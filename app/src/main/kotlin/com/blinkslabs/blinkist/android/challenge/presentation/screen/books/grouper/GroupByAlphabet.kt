package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.grouper

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Title

class GroupByAlphabet : BooksGrouper {

  override operator fun invoke(books: Books): GroupedBooks.ByAlphabet =
      books
          .filter { it.name.isNotBlank() }
          .groupBy { Title(it.name.first().toString().toUpperCase()) }
          .let { GroupedBooks.ByAlphabet(it) }
}