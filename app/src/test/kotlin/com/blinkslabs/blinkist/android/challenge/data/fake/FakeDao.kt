package com.blinkslabs.blinkist.android.challenge.data.fake

import com.blinkslabs.blinkist.android.challenge.data.book.datasource.local.BookDao
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BookEntity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * By this Fake we can mimic what Room does (emits updates for the data you're observing)
 */
class FakeDao : BookDao {

  private val emitter = PublishSubject.create<List<BookEntity>>()

  override fun insertBook(book: BookEntity): Long = 0L

  override fun insertAll(books: List<BookEntity>) = emitter.onNext(books)

  override fun getAllBooks(): Observable<List<BookEntity>> = emitter

}