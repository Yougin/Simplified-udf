package com.blinkslabs.blinkist.android.challenge.data.book.entity

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.threeten.bp.LocalDate

class BooksTypeConvertersShould {

  @Test fun `convert LocalDate into Long and back`() {
    val localDate = LocalDate.of(2019, 1, 1)

    val fromLocalDateToLong = BooksTypeConverters.fromOffsetDateTime(localDate)
    val fromLongToLocalDate = BooksTypeConverters.toOffsetDateTime(fromLocalDateToLong)

    assertThat(fromLongToLocalDate).isEqualTo(localDate)
  }

}