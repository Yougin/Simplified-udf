package com.blinkslabs.blinkist.android.challenge.presentation.sorter

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.grouper.GroupByDate
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.grouper.Title
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.grouper.Year
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDate

class GroupByDateTest {

  private lateinit var groupByDate: GroupByDate

  @Before fun setUp() {
    groupByDate = GroupByDate()
  }

  @Test fun `should group books by year`() {
    assertThat(groupByDate(fakeBooks).group).hasSize(4)
  }

  @Test fun `should books grouped by year contain correct amount of books grouped by week`() {
    val books = groupByDate(fakeBooks).group

    assertThat(books[Year(2014)]).isEqualTo(mapOf(Title("27") to listOf(book1)))

    assertThat(books[Year(2017)]).isEqualTo(mapOf(Title("52") to listOf(book2)))

    assertThat(books[Year(2018)]).isEqualTo(mapOf(
        Title("30") to listOf(book3),
        Title("27") to listOf(book4))
    )
    assertThat(books[Year(2019)]?.get(Title("27"))).hasSize(2)
    assertThat(books[Year(2019)]?.get(Title("27"))).contains(book5)
    assertThat(books[Year(2019)]?.get(Title("27"))).contains(book6)
  }

  @Test fun `should books grouped by week contain only books published this week`() {
    val books = groupByDate(fakeBooks).group

    assertThat(books[Year(2014)]?.get(Title("27"))).isEqualTo(listOf(book1))

    assertThat(books[Year(2017)]?.get(Title("52"))).isEqualTo(listOf(book2))

    assertThat(books[Year(2018)]?.get(Title("30"))).isEqualTo(listOf(book3))
    assertThat(books[Year(2018)]?.get(Title("27"))).isEqualTo(listOf(book4))

    assertThat(books[Year(2019)]?.get(Title("27"))).hasSize(2)
    assertThat(books[Year(2019)]?.get(Title("27"))).contains(book5)
    assertThat(books[Year(2019)]?.get(Title("27"))).contains(book6)
  }

  private val fakeBooks get() = listOf(book1, book2, book3, book4, book5, book6).shuffled()

  private val book1 get() = Book("id2", "aName", "", LocalDate.of(2014, 7, 2), "")
  private val book2 get() = Book("id5", "bName", "", LocalDate.of(2017, 12, 31), "")
  private val book3 get() = Book("id1", "aName", "", LocalDate.of(2018, 7, 23), "")
  private val book4 get() = Book("id3", "cName", "", LocalDate.of(2018, 7, 3), "")
  private val book5 get() = Book("id4", "zName", "", LocalDate.of(2019, 7, 3), "")
  private val book6 get() = Book("id5", "zName", "", LocalDate.of(2019, 7, 4), "")

}