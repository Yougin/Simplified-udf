package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blinkslabs.blinkist.android.challenge.R
import com.blinkslabs.blinkist.android.challenge.presentation.common.adapter.ItemDelegate
import kotlinx.android.synthetic.main.item_week_title.view.*

data class WeekTitle(val text: String)

class WeekTitleItemDelegate : ItemDelegate<WeekTitle> {

  override val associatedDataType: Class<WeekTitle> = WeekTitle::class.java

  override fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): RecyclerView.ViewHolder =
      ItemDelegate.Holder(layoutInflater.inflate(R.layout.item_week_title, parent, false))

  override fun bind(view: View, data: WeekTitle) {
    view.item_week_title_text_view.text = data.text
  }

}