package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Books

interface BooksGrouper {

  operator fun invoke(books: Books): GroupedBooks
}

sealed class GroupedBooks {
  data class ByDate(val group: YearlyGroup) : GroupedBooks()
  data class ByAlphabet(val group: AlphabeticGroup) : GroupedBooks()
}

typealias YearlyGroup = Map<Year, WeeklyGroup>

typealias AlphabeticGroup = Map<Title, Books>

