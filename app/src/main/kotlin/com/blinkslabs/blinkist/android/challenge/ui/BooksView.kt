package com.blinkslabs.blinkist.android.challenge.ui

import com.blinkslabs.blinkist.android.challenge.data.model.Books


interface BooksView {

    fun showBooks(books : Books)

    fun showErrorLoadingData()
}
