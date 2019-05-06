package com.blinkslabs.blinkist.android.challenge.fake

import com.blinkslabs.blinkist.android.challenge.data.book.datasource.local.BookDao
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BookEntity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/** This fake mimics real Room's behavior, i.e. it emits a value upon observable data change  */
class FakeDao : BookDao {

  private val emitter = PublishSubject.create<List<BookEntity>>()

  override fun insertBook(book: BookEntity): Long = 0L

  override fun insertAll(books: List<BookEntity>) = emitter.onNext(books)

  override fun getAllBooks(): Observable<List<BookEntity>> = emitter

  override fun deleteAllBooks() {}

}