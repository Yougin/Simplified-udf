package com.blinkslabs.blinkist.android.challenge.ui

import com.blinkslabs.blinkist.android.challenge.domain.book.Books


interface BooksView {

    fun showBooks(books : Books)

    fun showErrorLoadingData()
}
