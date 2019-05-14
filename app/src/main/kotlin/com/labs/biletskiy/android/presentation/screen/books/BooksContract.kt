package com.labs.biletskiy.android.presentation.screen.books

import com.labs.biletskiy.android.domain.book.model.Books
import com.labs.biletskiy.android.domain.featurewitch.GroupByWeeklyFeature

sealed class BooksIntent {

  object InitialIntent : BooksIntent()

  object ForceUpdateIntent : BooksIntent()
}

sealed class BooksViewState {

  object InFlight : BooksViewState()

  data class DataFetched(
      val books: Books,
      val weeklyFeature: GroupByWeeklyFeature
  ) : BooksViewState()

  data class Error(val throwable: Throwable) : BooksViewState()
}
