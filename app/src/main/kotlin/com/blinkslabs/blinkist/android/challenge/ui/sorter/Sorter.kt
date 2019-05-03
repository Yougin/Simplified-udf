package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Book
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.temporal.WeekFields

inline class Title(val value: String)

inline class Year(val value: Int)

typealias Books = List<Book>

typealias WeeklySection = Map<Title, Books>

typealias YearlySection = Map<Year, WeeklySection>

typealias OrderedMap = LinkedHashMap<Year, WeeklySection>

class WeeklySorter {

  fun sort(books: Books): YearlySection =
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

  private fun groupByWeek(books: Books) =
      books.groupBy {
        val weekNumber = getWeekNumber(it.publishDate).toString()
        Title(weekNumber)
      }

  private fun getWeekNumber(publishDate: LocalDate): Int {
    val week = WeekFields.of(DayOfWeek.MONDAY, 1)
    val weekOfYear = week.weekOfYear()
    return publishDate.get(weekOfYear)
  }

}
