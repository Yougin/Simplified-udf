package com.blinkslabs.blinkist.android.challenge.data

import com.blinkslabs.blinkist.android.challenge.data.api.BooksApi
import com.blinkslabs.blinkist.android.challenge.data.model.Book
import io.reactivex.Single
import javax.inject.Inject


class BooksService @Inject constructor(private val booksApi: BooksApi) {

    fun getBooks(): Single<List<Book>> = booksApi.getAllBooks()
}
