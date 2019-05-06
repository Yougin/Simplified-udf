package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter

import androidx.recyclerview.widget.RecyclerView
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.GroupByWeeklyFeature
import com.blinkslabs.blinkist.android.challenge.presentation.common.adapter.FlexibleAdapterImpl
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.delegate.*
import javax.inject.Inject

interface BooksAdapter {

  fun setBooks(books: Books, weeklyFeature: GroupByWeeklyFeature)

  fun asRecyclerViewAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>

}

class BooksAdapterImpl @Inject constructor() : FlexibleAdapterImpl(
    YearTitleItemDelegate(),
    WeekTitleItemDelegate(),
    BookCardItemDelegate(),
    AlphabetTitleItemDelegate()
), BooksAdapter {

  override fun setBooks(books: Books, weeklyFeature: GroupByWeeklyFeature) {
    items = convertToAdapterData(books, weeklyFeature).filterIsInstance(BookCard::class.java)
    notifyDataSetChanged()
  }

  override fun asRecyclerViewAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> = this
}
