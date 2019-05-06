package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.GroupByWeeklyFeature
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.threeten.bp.LocalDate

class BooksAdapterKtShould {

  @Test fun `return list of adapter data grouped by year and week`() {
    val result = convertToAdapterData(fakeBooks, GroupByWeeklyFeature.On)

    assertThat(result[0]).isEqualTo(YearTitle("2014"))
    assertThat(result[1]).isEqualTo(WeekTitle("27"))
    assertThat(result[2]).isEqualTo(BookCard(book1))

    assertThat(result[3]).isEqualTo(YearTitle("2017"))
    assertThat(result[4]).isEqualTo(WeekTitle("52"))
    assertThat(result[5]).isEqualTo(BookCard(book2))

    assertThat(result[6]).isEqualTo(YearTitle("2018"))
    assertThat(result[7]).isEqualTo(WeekTitle("27"))
    assertThat(result[8]).isEqualTo(BookCard(book4))
    assertThat(result[9]).isEqualTo(WeekTitle("30"))
    assertThat(result[10]).isEqualTo(BookCard(book3))

    assertThat(result[11]).isEqualTo(YearTitle("2019"))
    assertThat(result[12]).isEqualTo(WeekTitle("27"))
    assertThat(result[13]).isEqualTo(BookCard(book5))
    assertThat(result[14]).isEqualTo(BookCard(book6))

  }
}

private val fakeBooks get() = listOf(book1, book2, book3, book4, book5, book6).shuffled()

private val book1 get() = Book("id2", "week27", "", LocalDate.of(2014, 7, 2), "")
private val book2 get() = Book("id5", "week52", "", LocalDate.of(2017, 12, 31), "")
private val book3 get() = Book("id1", "week30", "", LocalDate.of(2018, 7, 23), "")
private val book4 get() = Book("id3", "week27", "", LocalDate.of(2018, 7, 3), "")
private val book5 get() = Book("id4", "week27", "", LocalDate.of(2019, 7, 3), "")
private val book6 get() = Book("id5", "week27", "", LocalDate.of(2019, 7, 4), "")