package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blinkslabs.blinkist.android.challenge.presentation.common.adapter.ItemDelegate

data class WeekTitle(val text: String)

class WeekTitleItemDelegate : ItemDelegate<WeekTitle> {
  override val associatedDataType: Class<WeekTitle>
    get() = TODO("not implemented")

  override fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): RecyclerView.ViewHolder {
    TODO("implement")
  }

  override fun bind(view: View, data: WeekTitle) {
  }

}