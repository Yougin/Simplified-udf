package com.blinkslabs.blinkist.android.challenge.ui

import com.blinkslabs.blinkist.android.challenge.data.model.Book


interface BooksView {

    fun showBooks(books : List<Book>)

    fun showErrorLoadingData()
}
