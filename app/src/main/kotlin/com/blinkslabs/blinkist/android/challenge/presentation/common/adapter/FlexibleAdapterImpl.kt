package com.blinkslabs.blinkist.android.challenge.presentation.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class FlexibleAdapterImpl(
    vararg delegates: ItemDelegate<*>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    FlexibleAdapter {

  override var items = listOf<Any>()
    set(value) {
      field = value
      verifyDelegateSupport()
    }
  private val delegationMap = DelegationMap(*delegates)
  private var layoutInflater: LayoutInflater? = null

  override fun asRecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> = this

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    holder.itemView?.let { delegationMap.bind(it, items[position]) }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    if (layoutInflater == null) {
      layoutInflater = LayoutInflater.from(parent.context)
    }
    return layoutInflater?.let { delegationMap[viewType].create(it, parent) }
        ?: throw IllegalStateException("No layout inflater can be constructed")
  }

  override fun getItemViewType(position: Int): Int = delegationMap.viewType(items[position])

  override fun getItemCount(): Int = items.size

  private fun verifyDelegateSupport() {
    val itemTypes = items.map { it::class.java }.toSet()
    val unsupportedTypes = itemTypes.filterNot { it in delegationMap.supportedTypes }
    if (unsupportedTypes.isNotEmpty()) {
      val missingTypeNames = unsupportedTypes.joinToString(separator = ", ") { it.simpleName }
      throw MissingDelegateException("Missing delegates for types: $missingTypeNames")
    }
  }

  override fun getDelegateForItem(atPosition: Int) = delegationMap[getItemViewType(atPosition)]
}

private class DelegationMap(vararg delegates: ItemDelegate<*>) {

  val supportedTypes = delegates.map { it.associatedDataType }.toSet()

  private val backingMap: Map<Int, ItemDelegate<*>> =
      delegates.map { it.associatedDataType.hashCode() to it }.toMap()

  fun <T : Any> viewType(forData: T) = forData::class.hashCode()

  // TODO-eugene we don't need reified here anymore
  @Suppress("UNCHECKED_CAST")
  inline fun <reified T> bind(view: View, data: T) =
      (this[viewType(data as Any)] as ItemDelegate<T>).bind(view, data)

  operator fun get(forViewType: Int) =
      backingMap[forViewType]
          ?: throw IllegalArgumentException("Data type not supported by any delegate")
}