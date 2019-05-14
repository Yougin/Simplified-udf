package com.labs.biletskiy.android.domain.book.usecase

import com.labs.biletskiy.android.data.book.datasource.BookRepository
import io.reactivex.Completable
import javax.inject.Inject

/** Sends a command to fetch Books from the web, make sure to subscribe for Books
 * updates using GetBooks.kt */
interface RefreshBooksByForce {

  operator fun invoke(): Completable
}

class RefreshBooksByBruteForce @Inject constructor(
    private val booksRepository: BookRepository
) : RefreshBooksByForce {

  override fun invoke(): Completable = Completable.fromCallable { booksRepository.fetchBooks() }
}
