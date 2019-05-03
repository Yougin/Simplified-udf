package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Book
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.temporal.WeekFields

inline class Title(val title: String)

class WeeklySorter {

  fun sort(books: List<Book>): Map<Int, Map<Title, List<Book>>> {
    val booksByYear: Map<Int, List<Book>> = books
        .sortedBy { it.publishDate.year }
        .groupBy { it.publishDate.year }

    val result: LinkedHashMap<Int, Map<Title, List<Book>>> = LinkedHashMap()
    booksByYear.forEach { (k, v) ->
      result[k] = v.groupBy {
        val weekNumber = getWeekNumber(it.publishDate).toString()
        Title(weekNumber)
      }
    }

    return result
  }

  private fun getWeekNumber(publishDate: LocalDate): Int {
    val week = WeekFields.of(DayOfWeek.MONDAY, 1)
    val weekOfYear = week.weekOfYear()
    return publishDate.get(weekOfYear)
  }

}
