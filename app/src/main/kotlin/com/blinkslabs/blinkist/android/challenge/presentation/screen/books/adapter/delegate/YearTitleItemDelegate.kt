package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blinkslabs.blinkist.android.challenge.presentation.common.adapter.ItemDelegate

data class YearTitle(val text: String)

class YearTitleItemDelegate : ItemDelegate<YearTitle> {

  override val associatedDataType: Class<YearTitle>
    get() = TODO("not implemented")

  override fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): RecyclerView.ViewHolder {
    TODO("not implemented")
  }

  override fun bind(view: View, data: YearTitle) {
  }

}