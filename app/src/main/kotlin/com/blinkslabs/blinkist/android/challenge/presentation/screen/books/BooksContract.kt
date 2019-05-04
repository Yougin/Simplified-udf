package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books

sealed class BooksViewState {
  object InFlight : BooksViewState()
  data class DataFetched(val books: Books, val isFeatureOn: Boolean) : BooksViewState()
  data class Error(val throwable: Throwable) : BooksViewState()
}

sealed class BooksIntent {
  object InitialIntent : BooksIntent()
  object ForceUpdateIntent : BooksIntent()
}
