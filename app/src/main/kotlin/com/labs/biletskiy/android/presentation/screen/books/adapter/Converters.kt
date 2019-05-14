package com.labs.biletskiy.android.presentation.screen.books.adapter

import com.labs.biletskiy.android.domain.book.model.Books
import com.labs.biletskiy.android.domain.featurewitch.GroupByWeeklyFeature
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.AlphabetTitle
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.BookCard
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.WeekTitle
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.YearTitle
import com.labs.biletskiy.android.presentation.screen.books.grouper.*

fun convertToAdapterData(
    books: Books, weeklyFeature: GroupByWeeklyFeature
): List<Any> =
    when (weeklyFeature) {
      GroupByWeeklyFeature.On -> convert(groupByDate(books))
      GroupByWeeklyFeature.Off -> convert(groupByAlphabet(books))
    }


fun convert(groupedBooks: GroupedBooks.ByDate): List<Any> {
  val groupOfBooks: YearlyGroup = groupedBooks.group

  val list = mutableListOf<Any>()
  groupOfBooks.forEach { (year, weeklyGroup) ->
    list += YearTitle(year.value.toString())

    weeklyGroup.forEach { (title, books) ->
      list += WeekTitle(title.value)
      list += BookCard(books)
    }
  }

  return list
}

fun convert(groupedBooks: GroupedBooks.ByAlphabet): List<Any> {
  val groupOfBooks: AlphabeticGroup = groupedBooks.group

  val list = mutableListOf<Any>()
  groupOfBooks.forEach { (title, books) ->
    list += AlphabetTitle(title.value)
    list += BookCard(books)
  }

  return list
}