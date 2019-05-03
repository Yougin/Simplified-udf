package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books


interface BooksView {

  fun showBooks(books: Books)

  fun showErrorLoadingData()
}
