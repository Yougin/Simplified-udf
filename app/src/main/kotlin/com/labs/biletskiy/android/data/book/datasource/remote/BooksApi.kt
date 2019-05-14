package com.labs.biletskiy.android.data.book.datasource.remote

import com.labs.biletskiy.android.domain.book.model.Books
import io.reactivex.Single


interface BooksApi {

  fun fetchAllBooks(): Single<Books>
}
