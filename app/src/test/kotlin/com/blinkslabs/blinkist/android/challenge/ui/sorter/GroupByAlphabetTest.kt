package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Book
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDate

class GroupByAlphabetTest {

  private lateinit var groupByAlphabet: GroupByAlphabet

  @Before fun setUp() {
    groupByAlphabet = GroupByAlphabet()
  }

  @Test fun `should books grouped alphabetically contain right amount of groups based on data`() {
    assertThat(groupByAlphabet(fakeBooks)).hasSize(4)
  }

  @Test fun `should all the books grouped alphabetically be present in results`() {
    val books = groupByAlphabet(fakeBooks)

    assertThat(books[Title("A")]).containsAllIn(listOf(book1, book3))
    assertThat(books[Title("B")]).containsAllIn(listOf(book2))
    assertThat(books[Title("C")]).containsAllIn(listOf(book4))
    assertThat(books[Title("Z")]).containsAllIn(listOf(book5, book6))
  }

  @Test fun `should skip a book with no name`(){
    val noNameBook = Book("id2", "  ", "", LocalDate.of(2014, 7, 2), "")

    assertThat(groupByAlphabet(listOf(noNameBook))).isEmpty()
  }

  private val fakeBooks get() = listOf(book1, book2, book3, book4, book5, book6).shuffled()

  private val book1 get() = Book("id2", "aName", "", LocalDate.of(2014, 7, 2), "")
  private val book3 get() = Book("id1", "aName", "", LocalDate.of(2018, 7, 23), "")
  private val book2 get() = Book("id5", "bName", "", LocalDate.of(2017, 12, 31), "")
  private val book4 get() = Book("id3", "cName", "", LocalDate.of(2018, 7, 3), "")
  private val book5 get() = Book("id4", "zName", "", LocalDate.of(2019, 7, 3), "")
  private val book6 get() = Book("id5", "zName", "", LocalDate.of(2019, 7, 4), "")
}