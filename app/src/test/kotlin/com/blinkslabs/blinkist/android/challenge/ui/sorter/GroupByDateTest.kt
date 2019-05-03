package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Book
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDate

class GroupByDateTest {

  private lateinit var groupByDate: GroupByDate

  private val fakeBooks get() = listOf(book1, book2, book3, book4, book5, book6).shuffled()

  @Before fun setUp() {
    groupByDate = GroupByDate()
  }

  @Test fun `should group books by year`() {
    assertThat(groupByDate(fakeBooks)).hasSize(4)
  }

  @Test fun `should books grouped by year contain correct amount of books grouped by week`() {
    val books = groupByDate(fakeBooks)

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
    val books = groupByDate(fakeBooks)

    assertThat(books[Year(2014)]?.get(Title("27"))).isEqualTo(listOf(book1))
    assertThat(books[Year(2017)]?.get(Title("52"))).isEqualTo(listOf(book2))
    assertThat(books[Year(2018)]?.get(Title("30"))).isEqualTo(listOf(book3))
    assertThat(books[Year(2018)]?.get(Title("27"))).isEqualTo(listOf(book4))

    assertThat(books[Year(2019)]?.get(Title("27"))).hasSize(2)
    assertThat(books[Year(2019)]?.get(Title("27"))).contains(book5)
    assertThat(books[Year(2019)]?.get(Title("27"))).contains(book6)
  }

  private val book1 get() = Book("id2", "w = 27, y = 2014", "B", LocalDate.of(2014, 7, 2), "url")
  private val book2 get() = Book("id5", "w = 52, y = 2017", "B", LocalDate.of(2017, 12, 31), "url")
  private val book3 get() = Book("id1", "w = 30, y = 2018", "A", LocalDate.of(2018, 7, 23), "url")
  private val book4 get() = Book("id3", "w = 27, y = 2018", "C", LocalDate.of(2018, 7, 3), "url")
  private val book5 get() = Book("id4", "w = 27, y = 2019", "E", LocalDate.of(2019, 7, 3), "url")
  private val book6 get() = Book("id5", "w = 27, y = 2019", "F", LocalDate.of(2019, 7, 4), "url")

}