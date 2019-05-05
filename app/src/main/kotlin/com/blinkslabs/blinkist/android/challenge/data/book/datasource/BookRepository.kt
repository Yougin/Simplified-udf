package com.blinkslabs.blinkist.android.challenge.data.book.datasource

import arrow.core.Option
import com.blinkslabs.blinkist.android.challenge.data.book.datasource.local.BookDao
import com.blinkslabs.blinkist.android.challenge.data.book.datasource.remote.BooksApi
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import io.reactivex.Observable
import javax.inject.Inject

interface BookRepository {

  fun fetchAllBooks(): Observable<Option<Books>>

  fun forceFetch(): Unit
}

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val booksApi: BooksApi
) : BookRepository {

  override fun fetchAllBooks(): Observable<Option<Books>> {
    return Observable.empty()
  }

  override fun forceFetch() {
  }

}