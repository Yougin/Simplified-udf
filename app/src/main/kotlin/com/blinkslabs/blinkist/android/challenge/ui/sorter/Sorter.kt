package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Book
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.temporal.WeekFields

inline class Title(val title: String)

class WeeklySorter {

  fun sort(books: List<Book>): Map<Int, Map<Title, List<Book>>> =
      with(LinkedHashMap<Int, Map<Title, List<Book>>>()) {
        books
            .sortedBy { book -> book.publishYear }
            .groupBy { book -> book.publishYear }
            .forEach { (year, booksThisYear) ->
              this[year] = booksThisYear.groupBy {
                val weekNumber = getWeekNumber(it.publishDate).toString()
                Title(weekNumber)
              }
            }
        this
      }

  private fun getWeekNumber(publishDate: LocalDate): Int {
    val week = WeekFields.of(DayOfWeek.MONDAY, 1)
    val weekOfYear = week.weekOfYear()
    return publishDate.get(weekOfYear)
  }

}
