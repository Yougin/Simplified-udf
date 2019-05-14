package com.labs.biletskiy.android.data.book.datasource

import arrow.core.Option
import com.labs.biletskiy.android.data.book.datasource.local.BookDao
import com.labs.biletskiy.android.data.book.datasource.remote.BooksApi
import com.labs.biletskiy.android.domain.book.model.Books
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observable.just
import timber.log.Timber
import javax.inject.Inject

interface BookRepository {

  fun getAllBooks(): Observable<Option<Books>>

  fun fetchBooks(): Completable
}

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val booksApi: BooksApi
) : BookRepository {

  override fun getAllBooks(): Observable<Option<Books>> =
      bookDao.getAllBooks()
          .doOnEach { Timber.d("Fetching from the database") }
          .map {
            if (it.isEmpty()) Option.empty()
            else Option(it.toBooks())
          }

  // TODO: do mapping on computation thread
  override fun fetchBooks(): Completable =
      just(bookDao.deleteAllBooks())
          .flatMapCompletable {
            booksApi.fetchAllBooks()
                .doOnSuccess { Timber.d("Fetching from the Network") }
                .toObservable()
                .flatMapIterable { it }
                .map { it.toEntity() }
                .toList()
                .doOnSuccess { entities -> bookDao.insertAll(entities) }
                .ignoreElement()
          }
}