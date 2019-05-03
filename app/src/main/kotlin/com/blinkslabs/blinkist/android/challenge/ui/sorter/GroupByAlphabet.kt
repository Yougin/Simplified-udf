package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Books

class GroupByAlphabet : BooksGrouper {

  override operator fun invoke(books: Books): GroupedBooks.ByAlphabet =
      books
          .filter { it.name.isNotBlank() }
          .groupBy { Title(it.name.first().toString().toUpperCase()) }
          .let { GroupedBooks.ByAlphabet(it) }
}