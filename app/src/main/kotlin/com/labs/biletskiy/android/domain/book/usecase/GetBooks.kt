package com.labs.biletskiy.android.domain.book.usecase

import arrow.core.Option
import arrow.core.getOrElse
import com.labs.biletskiy.android.data.book.datasource.BookRepository
import com.labs.biletskiy.android.domain.book.model.Books
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.Single.just
import javax.inject.Inject

/** Emits every change of the Books persisted in the database.
 * It fetches from Remote if no Books persisted */
interface GetBooks {

  operator fun invoke(): Observable<Books>
}

class GetBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) : GetBooks {

  override operator fun invoke(): Observable<Books> =
      bookRepository
          .getAllBooks()
          .flatMapSingle { fetchIfEmpty(it) }
          .filter { it.nonEmpty() }
          .map {
            it.getOrElse {
              throw IllegalStateException("None can't be unwrapped, check for emptiness first")
            }
          }

  private fun fetchIfEmpty(books: Option<Books>): Single<Option<Books>> =
      when {
        books.isEmpty() -> bookRepository.fetchBooks()
        else -> Completable.complete()
      }.andThen(just(books))
}
