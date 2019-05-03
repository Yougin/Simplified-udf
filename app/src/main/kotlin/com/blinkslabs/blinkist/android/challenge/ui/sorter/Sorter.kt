package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Books
import com.blinkslabs.blinkist.android.challenge.data.model.publishWeek
import com.blinkslabs.blinkist.android.challenge.data.model.publishYear
import java.util.*

interface BooksGrouper {

  operator fun invoke(books: Books): GroupedBooks
}

sealed class GroupedBooks {
  data class ByDate(val group: YearlyGroup) : GroupedBooks()
  data class ByAlphabet(val group: AlphabeticGroup) : GroupedBooks()
}

class GroupByDate : BooksGrouper {

  override operator fun invoke(books: Books): GroupedBooks.ByDate =
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

  private fun groupByWeek(books: Books): WeeklyGroup = books.groupBy { Title(it.publishWeek) }

}

class GroupByAlphabet : BooksGrouper {

  override operator fun invoke(books: Books): GroupedBooks.ByAlphabet =
      books
          .filter { it.name.isNotBlank() }
          .groupBy { Title(it.name.first().toString().toUpperCase()) }
          .let { GroupedBooks.ByAlphabet(it) }
}
