package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Book
import com.blinkslabs.blinkist.android.challenge.data.model.publishWeek
import com.blinkslabs.blinkist.android.challenge.data.model.publishYear

inline class Title(val value: String)

inline class Year(val value: Int)

typealias Books = List<Book>

typealias WeeklySection = Map<Title, Books>

typealias YearlySection = Map<Year, WeeklySection>

typealias OrderedMap = LinkedHashMap<Year, WeeklySection>

class GroupByDate {

  operator fun invoke(books: Books): YearlySection =
      with(OrderedMap()) {
        books
            .sortedBy { book -> book.publishYear }
            .groupBy { book -> book.publishYear }
            .forEach { (year, booksPublishedThisYear) ->
              val forThisYear = Year(year)
              this[forThisYear] = groupByWeek(booksPublishedThisYear)
            }
        this
      }

  private fun groupByWeek(books: Books): WeeklySection = books.groupBy { Title(it.publishWeek) }

}
