package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.GroupByWeeklyFeature
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.delegate.AlphabetTitle
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.delegate.BookCard
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.delegate.WeekTitle
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.delegate.YearTitle
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.grouper.*

fun convertToAdapterData(
    books: Books, weeklyFeature: GroupByWeeklyFeature
): List<*> =
    when (weeklyFeature) {
      GroupByWeeklyFeature.On -> convert(groupByDate(books))
      GroupByWeeklyFeature.Off -> convert(groupByAlphabet(books))
    }


fun convert(groupedBooks: GroupedBooks.ByDate): List<*> {
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

fun convert(groupedBooks: GroupedBooks.ByAlphabet): List<*> {
  val groupOfBooks: AlphabeticGroup = groupedBooks.group

  val list = mutableListOf<Any>()
  groupOfBooks.forEach { (title, books) ->
    list += AlphabetTitle(title.value)
    list += BookCard(books)
  }

  return list
}