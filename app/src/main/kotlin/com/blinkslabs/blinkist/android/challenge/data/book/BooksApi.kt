package com.blinkslabs.blinkist.android.challenge.data.book

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import io.reactivex.Single


interface BooksApi {

    fun getAllBooks(): Single<Books>
}
