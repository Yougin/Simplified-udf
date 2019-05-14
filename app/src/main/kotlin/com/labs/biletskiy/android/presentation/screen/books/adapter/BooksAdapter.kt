package com.labs.biletskiy.android.presentation.screen.books.adapter

import androidx.recyclerview.widget.RecyclerView
import com.labs.biletskiy.android.domain.book.model.Books
import com.labs.biletskiy.android.domain.featurewitch.GroupByWeeklyFeature
import com.labs.biletskiy.android.presentation.common.adapter.FlexibleAdapterImpl
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.AlphabetTitleItemDelegate
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.BookCardItemDelegate
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.WeekTitleItemDelegate
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.YearTitleItemDelegate
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
    items = convertToAdapterData(books, weeklyFeature)
    notifyDataSetChanged()
  }

  override fun asRecyclerViewAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> = this
}
