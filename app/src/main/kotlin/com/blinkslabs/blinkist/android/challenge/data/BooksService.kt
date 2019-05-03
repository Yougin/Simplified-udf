package com.blinkslabs.blinkist.android.challenge.data

import com.blinkslabs.blinkist.android.challenge.data.api.BooksApi
import com.blinkslabs.blinkist.android.challenge.data.model.Books
import io.reactivex.Single
import javax.inject.Inject


class BooksService @Inject constructor(private val booksApi: BooksApi) {

    fun getBooks(): Single<Books> = booksApi.getAllBooks()
}
