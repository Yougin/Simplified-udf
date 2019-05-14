package com.labs.biletskiy.android.presentation.common.adapter

import androidx.recyclerview.widget.RecyclerView

interface FlexibleAdapter {

  var items: List<Any>

  fun asRecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>

  fun getItemCount(): Int

  fun notifyDataSetChanged()

  fun getDelegateForItem(atPosition: Int): ItemDelegate<*>
}