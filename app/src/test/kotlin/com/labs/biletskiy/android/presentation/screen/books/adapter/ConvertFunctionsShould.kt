package com.labs.biletskiy.android.presentation.screen.books.adapter

import com.google.common.truth.Truth.assertThat
import com.labs.biletskiy.android.domain.book.model.Book
import com.labs.biletskiy.android.domain.featurewitch.GroupByWeeklyFeature
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.AlphabetTitle
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.BookCard
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.WeekTitle
import com.labs.biletskiy.android.presentation.screen.books.adapter.delegate.YearTitle
import org.junit.Test
import org.threeten.bp.LocalDate

class ConvertFunctionsShould {

  @Test fun `return list of adapter data grouped by year and week`() {
    val result = convertToAdapterData(fakeBooks, GroupByWeeklyFeature.On)

    assertThat(result[0]).isEqualTo(YearTitle("2014"))
    assertThat(result[1]).isEqualTo(WeekTitle("27"))
    assertThat(result[2]).isEqualTo(BookCard(listOf(book1)))

    assertThat(result[3]).isEqualTo(YearTitle("2017"))
    assertThat(result[4]).isEqualTo(WeekTitle("52"))
    assertThat(result[5]).isEqualTo(BookCard(listOf(book2)))

    assertThat(result[6]).isEqualTo(YearTitle("2018"))
    assertThat(result[7]).isEqualTo(WeekTitle("27"))
    assertThat(result[8]).isEqualTo(BookCard(listOf(book4)))
    assertThat(result[9]).isEqualTo(WeekTitle("30"))
    assertThat(result[10]).isEqualTo(BookCard(listOf(book3)))

    assertThat(result[11]).isEqualTo(YearTitle("2019"))
    assertThat(result[12]).isEqualTo(WeekTitle("27"))
    assertThat(result[13]).isEqualTo(BookCard(listOf(book5, book6)))
  }

  @Test fun `return list of adapter data grouped alphabetically`() {
    val result = convertToAdapterData(fakeBooks, GroupByWeeklyFeature.Off)

    assertThat(result[0]).isEqualTo(AlphabetTitle("A"))
    assertThat(result[1]).isEqualTo(BookCard(listOf(book1, book2)))

    assertThat(result[2]).isEqualTo(AlphabetTitle("B"))
    assertThat(result[3]).isEqualTo(BookCard(listOf(book3)))

    assertThat(result[4]).isEqualTo(AlphabetTitle("C"))
    assertThat(result[5]).isEqualTo(BookCard(listOf(book4)))

    assertThat(result[6]).isEqualTo(AlphabetTitle("D"))
    assertThat(result[7]).isEqualTo(BookCard(listOf(book5, book6)))
  }
}

private val fakeBooks get() = listOf(book1, book2, book3, book4, book5, book6).shuffled()

private val book1 get() = Book("id1", "a week27", "", LocalDate.of(2014, 7, 2), "")
private val book2 get() = Book("id2", "a week52", "", LocalDate.of(2017, 12, 31), "")
private val book3 get() = Book("id3", "b week30", "", LocalDate.of(2018, 7, 23), "")
private val book4 get() = Book("id4", "c week27", "", LocalDate.of(2018, 7, 3), "")
private val book5 get() = Book("id5", "d week27", "", LocalDate.of(2019, 7, 3), "")
private val book6 get() = Book("id6", "d week27", "", LocalDate.of(2019, 7, 4), "")