package com.labs.biletskiy.android.presentation.screen.books.grouper

import com.labs.biletskiy.android.domain.book.model.Books
import com.labs.biletskiy.android.domain.book.model.publishWeek
import com.labs.biletskiy.android.domain.book.model.publishYear
import java.util.*

typealias WeeklyGroup = Map<Title, Books>

inline class Year(val value: Int)

fun groupByDate(books: Books): GroupedBooks.ByDate =
    with(LinkedHashMap<Year, WeeklyGroup>()) {
      books
          .sortedBy { book -> book.publishYear }
          .groupBy { book -> book.publishYear }
          .forEach { (year, booksPublishedThisYear) ->
            val forThisYear = Year(year)
            this[forThisYear] = groupByWeek(booksPublishedThisYear)
          }
          .let { GroupedBooks.ByDate(this) }
    }

private fun groupByWeek(books: Books): WeeklyGroup =
    books
        .sortedBy { it.publishDate }
        .groupBy { Title(it.publishWeek) }
