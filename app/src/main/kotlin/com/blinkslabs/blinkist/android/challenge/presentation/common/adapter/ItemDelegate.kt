package com.blinkslabs.blinkist.android.challenge.presentation.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ItemDelegate<D> {

  val associatedDataType: Class<D>

  fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): RecyclerView.ViewHolder

  fun bind(view: View, data: D)

  class Holder(view: View) : RecyclerView.ViewHolder(view)
}