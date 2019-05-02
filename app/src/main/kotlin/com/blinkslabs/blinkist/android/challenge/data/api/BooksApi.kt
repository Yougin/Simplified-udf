package com.blinkslabs.blinkist.android.challenge.data.api

import com.blinkslabs.blinkist.android.challenge.data.model.Book
import io.reactivex.Single


interface BooksApi {

    fun getAllBooks(): Single<List<Book>>
}
