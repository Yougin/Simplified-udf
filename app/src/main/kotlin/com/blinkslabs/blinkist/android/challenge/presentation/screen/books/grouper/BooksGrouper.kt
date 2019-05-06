package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.grouper

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books

typealias YearlyGroup = Map<Year, WeeklyGroup>

typealias AlphabeticGroup = Map<Title, Books>

inline class Title(val value: String)

interface BooksGrouper {
  fun groupByDate(books: Books): GroupedBooks
}

sealed class GroupedBooks {
  data class ByDate(val group: YearlyGroup) : GroupedBooks()
  data class ByAlphabet(val group: AlphabeticGroup) : GroupedBooks()
}

