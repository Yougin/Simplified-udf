package com.blinkslabs.blinkist.android.challenge.domain.book.usecase

import arrow.core.Option
import com.blinkslabs.blinkist.android.challenge.data.book.datasource.BookRepository
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import io.reactivex.*
import io.reactivex.Single.just
import javax.inject.Inject

/** Emits updates of Books. It will fetch from Remote if no Books persisted */
interface GetBooks {

  operator fun invoke(): Observable<Books>
}

class GetBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) : GetBooks {

  override operator fun invoke(): Observable<Books> =
      bookRepository
          .getAllBooks()
          .flatMapSingle(::fetchIfEmptyOrEmitIfSome)
          .compose(UnwrapOptionTransformer())

  private fun fetchIfEmptyOrEmitIfSome(books: Option<Books>): Single<Option<Books>> =
      fetchWhenNone(books).andThen(just(books))

  private fun fetchWhenNone(books: Option<Books>): Completable =
      when {
        books.isEmpty() -> bookRepository.fetchBooks()
        else -> Completable.complete()
      }
}

class UnwrapOptionTransformer<T> : ObservableTransformer<Option<T>, T> {
  override fun apply(upstream: Observable<Option<T>>): ObservableSource<T> =
      upstream.filter { it.isEmpty().not() }.map { it.orNull() }
}
