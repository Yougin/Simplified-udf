package com.labs.biletskiy.android.fake

import arrow.core.Option
import com.labs.biletskiy.android.data.book.datasource.BookRepository
import com.labs.biletskiy.android.domain.book.model.Books
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/** This fake mimics behavior or the real repository (and Room lib), i.e. getAllBooks emits each
 * time fetchBooks is triggered */
class FakeBookRepository constructor(
    private val emitter: PublishSubject<Option<Books>>,
    private val fakeBooks: Books
) : BookRepository {

  override fun getAllBooks(): Observable<Option<Books>> {
    return emitter
  }

  override fun fetchBooks(): Completable {
    emitter.onNext(Option(fakeBooks))
    return Completable.complete()
  }
}