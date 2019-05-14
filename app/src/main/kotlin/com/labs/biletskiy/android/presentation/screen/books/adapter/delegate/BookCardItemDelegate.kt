package com.labs.biletskiy.android.presentation.screen.books.adapter.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.labs.biletskiy.android.challenge.R
import com.labs.biletskiy.android.domain.book.model.Books
import com.labs.biletskiy.android.presentation.common.adapter.ItemDelegate
import kotlinx.android.synthetic.main.item_book_card.view.*

data class BookCard(val books: Books)

class BookCardItemDelegate : ItemDelegate<BookCard> {

  override val associatedDataType: Class<BookCard> = BookCard::class.java

  override fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): RecyclerView.ViewHolder =
      ItemDelegate.Holder(layoutInflater.inflate(R.layout.item_book_card, parent, false))

  override fun bind(view: View, data: BookCard) {
    view.books_carousel.items = data.books
    view.books_carousel.scheduleLayoutAnimation()
  }
}