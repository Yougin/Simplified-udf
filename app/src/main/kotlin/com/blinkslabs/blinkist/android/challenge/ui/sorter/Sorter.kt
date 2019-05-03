package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Book
import com.blinkslabs.blinkist.android.challenge.data.model.publishWeek
import com.blinkslabs.blinkist.android.challenge.data.model.publishYear
import java.util.LinkedHashMap

inline class Title(val value: String)

inline class Year(val value: Int)

typealias Books = List<Book>

typealias WeeklyGroup = Map<Title, Books>

typealias YearlyGroup = Map<Year, WeeklyGroup>

class GroupByDate {

  operator fun invoke(books: Books): YearlyGroup =
      with(LinkedHashMap<Year, WeeklyGroup>()) {
        books
            .sortedBy { book -> book.publishYear }
            .groupBy { book -> book.publishYear }
            .forEach { (year, booksPublishedThisYear) ->
              val forThisYear = Year(year)
              this[forThisYear] = groupByWeek(booksPublishedThisYear)
            }
        this
      }

  private fun groupByWeek(books: Books): WeeklyGroup = books.groupBy { Title(it.publishWeek) }

}
