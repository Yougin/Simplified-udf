package com.blinkslabs.blinkist.android.challenge.data.book.datasource.remote

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import io.reactivex.Single


interface BooksApi {

  fun getAllBooks(): Single<Books>
}
