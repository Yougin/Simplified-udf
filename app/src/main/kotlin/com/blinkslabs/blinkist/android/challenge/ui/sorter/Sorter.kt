package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.api.getWeekNumber
import com.blinkslabs.blinkist.android.challenge.data.model.Book

inline class Title(val title: String)

class WeeklySorter {

  fun sort(books: List<Book>): LinkedHashMap<Int, Map<Title, List<Book>>> {
    val sortedBooks = books.sortedBy { it.publishDate.year }
    val groupedByYear = sortedBooks.groupBy { it.publishDate.year }

    val result: LinkedHashMap<Int, Map<Title, List<Book>>> = LinkedHashMap()
    for ((year, bookz) in groupedByYear) {
      result[year] = bookz.groupBy { Title(getWeekNumber(it.publishDate).toString()) }
    }

    return result
  }

}