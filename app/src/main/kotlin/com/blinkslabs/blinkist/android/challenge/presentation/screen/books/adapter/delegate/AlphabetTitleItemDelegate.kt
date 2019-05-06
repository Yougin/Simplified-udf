package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blinkslabs.blinkist.android.challenge.R
import com.blinkslabs.blinkist.android.challenge.presentation.common.adapter.ItemDelegate
import kotlinx.android.synthetic.main.item_alphabet_title.view.*

data class AlphabetTitle(val text: String)

class AlphabetTitleItemDelegate : ItemDelegate<AlphabetTitle> {

  override val associatedDataType: Class<AlphabetTitle> = AlphabetTitle::class.java

  override fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): RecyclerView.ViewHolder =
      ItemDelegate.Holder(layoutInflater.inflate(R.layout.item_alphabet_title, parent, false))

  override fun bind(view: View, data: AlphabetTitle) {
    view.item_alphabet_title.text = data.text
  }

}