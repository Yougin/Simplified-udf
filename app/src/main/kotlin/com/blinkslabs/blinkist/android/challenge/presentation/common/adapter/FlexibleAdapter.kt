package com.blinkslabs.blinkist.android.challenge.presentation.common.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface FlexibleAdapter {

  var items: List<Any>

  fun asRecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>

  fun getItemCount(): Int

  fun notifyDataSetChanged()

  fun getDelegateForItem(atPosition: Int): ItemDelegate<*>
}