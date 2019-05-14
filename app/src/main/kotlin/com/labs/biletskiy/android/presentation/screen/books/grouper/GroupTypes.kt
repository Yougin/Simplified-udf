package com.labs.biletskiy.android.presentation.screen.books.grouper

import com.labs.biletskiy.android.domain.book.model.Books

typealias YearlyGroup = Map<Year, WeeklyGroup>

typealias AlphabeticGroup = Map<Title, Books>

inline class Title(val value: String)

sealed class GroupedBooks {
  data class ByDate(val group: YearlyGroup) : GroupedBooks()
  data class ByAlphabet(val group: AlphabeticGroup) : GroupedBooks()
}

