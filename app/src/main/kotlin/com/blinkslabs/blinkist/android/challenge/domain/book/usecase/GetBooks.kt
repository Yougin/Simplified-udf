package com.blinkslabs.blinkist.android.challenge.domain.book.usecase

import com.blinkslabs.blinkist.android.challenge.data.book.BooksApi
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import io.reactivex.Single
import javax.inject.Inject

interface GetBooks {
  operator fun invoke(): Single<Books>
}

class GetBooksUseCase @Inject constructor(private val booksApi: BooksApi) : GetBooks {
  override operator fun invoke(): Single<Books> = booksApi.getAllBooks()
}
