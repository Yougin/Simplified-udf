package com.blinkslabs.blinkist.android.challenge.domain.book.usecase

import com.blinkslabs.blinkist.android.challenge.data.book.datasource.BookRepository
import io.reactivex.Completable
import javax.inject.Inject

/** Sends a command to fetch Books from the web, make sure to subscribe for Books
 * updates using GetBooks.kt */
interface UpdateBooksByForce {

  operator fun invoke(): Completable
}

class UpdateBooksByBruteForce @Inject constructor(
    private val booksRepository: BookRepository
) : UpdateBooksByForce {

  override fun invoke(): Completable = Completable.fromCallable { booksRepository.fetchBooks() }
}
