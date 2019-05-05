package com.blinkslabs.blinkist.android.challenge.data.book.datasource

import arrow.core.Option
import com.blinkslabs.blinkist.android.challenge.data.book.datasource.local.BookDao
import com.blinkslabs.blinkist.android.challenge.data.book.datasource.remote.BooksApi
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BookEntity
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import io.reactivex.Completable
import io.reactivex.Observable
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
      bookDao.getAllBooks().map { Option(it.toBooks()) }

  override fun fetchBooks(): Completable {
    return Completable.complete()
  }

}

fun BookEntity.toBook(): Book {
  return Book(this.id, this.name, this.author, this.publishDate, this.coverImageUrl)
}

fun List<BookEntity>.toBooks(): Books = this.map { it.toBook() }