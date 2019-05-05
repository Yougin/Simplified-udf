package com.blinkslabs.blinkist.android.challenge.domain.book.usecase

import com.blinkslabs.blinkist.android.challenge.data.book.datasource.remote.BooksApi
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

interface GetBooks {

  /** Emits updates of Books. It will fetch from Remote if no Books persisted */
  operator fun invoke(): Observable<Books>
}

class GetBooksUseCase @Inject constructor(private val booksApi: BooksApi) : GetBooks {

  override operator fun invoke(): Observable<Books> =
      booksApi.fetchAllBooks().toObservable().doOnNext { Timber.d("----- Emits $it") }
}
