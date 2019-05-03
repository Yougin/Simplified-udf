package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Book
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDate

class WeeklySorterTest {

  private lateinit var sorter: WeeklySorter

  @Before fun setUp() {
    sorter = WeeklySorter()
  }

  @Test fun `should sort books by year`() {
    val books = getBooks()

    val sortedBooks = sorter.sort(books)

    assertThat(sortedBooks).hasSize(4)
    assertThat(sortedBooks[2014]).hasSize(1)
    assertThat(sortedBooks[2017]).hasSize(1)
    assertThat(sortedBooks[2018]).hasSize(2)
    assertThat(sortedBooks[2019]).hasSize(1)

    println(sortedBooks)
  }

  @Test fun `should sort books by week`(){
    val books = getBooks()

    val sortedBooks = sorter.sort(books)

    println(sortedBooks)
    assertThat(sortedBooks[2014]?.get("27")).isEqualTo(listOf(book1))
    // TODO-eugene 52?
    assertThat(sortedBooks[2017]?.get("53")).isEqualTo(listOf(book2))
    assertThat(sortedBooks[2018]?.get("30")).isEqualTo(listOf(book3))
    assertThat(sortedBooks[2018]?.get("27")).isEqualTo(listOf(book4))

    assertThat(sortedBooks[2019]?.get("27")).hasSize(2)
    assertThat(sortedBooks[2019]?.get("27")).contains(book5)
    assertThat(sortedBooks[2019]?.get("27")).contains(book6)
  }

  private fun getBooks() = listOf(book1, book2, book3, book4, book5, book6).shuffled()

  /*
  2014={27=[w = 27, y = 2014]},
  2017={53=[w = 52, y = 2017]},
  2018={27=[w = 27, y = 2018], 30=[w = 30, y = 2018]},
  2019={27=[w = 27, y = 2019, w = 27, y = 2019]}
  */

  private val book1 get() = Book("id2", "w = 27, y = 2014", "B", LocalDate.of(2014, 7, 2), "url")
  private val book2 get() = Book("id5", "w = 52, y = 2017", "D", LocalDate.of(2017, 12, 31), "url")
  private val book3 get() = Book("id1", "w = 30, y = 2018", "A", LocalDate.of(2018, 7, 23), "url")
  private val book4 get() = Book("id3", "w = 27, y = 2018", "C", LocalDate.of(2018, 7, 3), "url")
  private val book5 get() = Book("id4", "w = 27, y = 2019", "E", LocalDate.of(2019, 7, 3), "url")
  private val book6 get() = Book("id5", "w = 27, y = 2019", "F", LocalDate.of(2019, 7, 4), "url")

}