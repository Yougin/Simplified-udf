package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blinkslabs.blinkist.android.challenge.R
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.presentation.common.adapter.ItemDelegate

data class BookCard(val book: Book)

class BookCardItemDelegate : ItemDelegate<BookCard> {

  override val associatedDataType: Class<BookCard> get() = BookCard::class.java

  override fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): RecyclerView.ViewHolder =
      ItemDelegate.Holder(layoutInflater.inflate(R.layout.item_book_card, parent, false))

  override fun bind(view: View, data: BookCard) { //    view.books_carousel.items = data
  }
}