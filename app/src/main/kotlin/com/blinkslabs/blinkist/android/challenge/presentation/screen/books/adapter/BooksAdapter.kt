package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.GroupByWeeklyFeature
import com.blinkslabs.blinkist.android.challenge.presentation.common.adapter.FlexibleAdapterImpl
import javax.inject.Inject

interface BooksAdapter {

  fun setBooks(books: Books, weeklyFeature: GroupByWeeklyFeature)
}

class BooksAdapterImpl @Inject constructor( // List Item Delegates in here
) : FlexibleAdapterImpl(), BooksAdapter {

  override fun setBooks(books: Books, weeklyFeature: GroupByWeeklyFeature) {
    convertDataToItemDelegates(books, weeklyFeature)
  }

}


fun convertDataToItemDelegates(books: Books, weeklyFeature: GroupByWeeklyFeature) {

}
