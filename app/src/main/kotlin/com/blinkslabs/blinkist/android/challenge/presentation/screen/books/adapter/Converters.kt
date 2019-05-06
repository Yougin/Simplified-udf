package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter

import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.grouper.AlphabeticGroup
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.grouper.GroupedBooks
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.grouper.YearlyGroup

fun convert(groupedBooks: GroupedBooks.ByDate): List<*> {
  val groupOfBooks: YearlyGroup = groupedBooks.group

  val list = mutableListOf<Any>()
  groupOfBooks.forEach { (year, weeklyGroup) ->
    list += YearTitle(year.value.toString())

    weeklyGroup.forEach { (title, books) ->
      list += WeekTitle(title.value)
      list.add(books.map { book -> BookCard(book) })
    }
  }

  return list
}

fun convert(groupedBooks: GroupedBooks.ByAlphabet): List<*> {
  val groupOfBooks: AlphabeticGroup = groupedBooks.group

  val list = mutableListOf<Any>()
  groupOfBooks.forEach { (title, books) ->
    list += AlphabetTitle(title.value)
    list.add(books.map { book -> BookCard(book) })
  }

  return list
}