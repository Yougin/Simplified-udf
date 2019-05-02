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

    /* {2014={27=[1st book week 27, y = 2014]},
    2018={30=[book week 30, y = 2018], 27=[2nd book week 27, y = 2018]},
    2019={27=[2019 also week 27, y = 2019]}} */
  }

  private fun getBooks() = listOf(
      Book("id2", "w = 27, y = 2014", "B", LocalDate.of(2014, 7, 2), "url"),
      Book("id5", "w = 27, y = 2019", "D", LocalDate.of(2017, 12, 31), "url"),
      Book("id1", "w = 30, y = 2018", "A", LocalDate.of(2018, 7, 23), "url"),
      Book("id3", "w = 27, y = 2018", "C", LocalDate.of(2018, 7, 3), "url"),
      Book("id4", "w = 27, y = 2019", "D", LocalDate.of(2019, 7, 3), "url")
  ).shuffled()

}