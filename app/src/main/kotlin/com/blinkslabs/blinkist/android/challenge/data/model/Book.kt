package com.blinkslabs.blinkist.android.challenge.data.model

import org.threeten.bp.LocalDate
import org.threeten.bp.temporal.WeekFields

typealias Books = List<Book>

data class Book(
    val id: String,
    val name: String,
    val author: String,
    val publishDate: LocalDate,
    val coverImageUrl: String
)

val Book.publishWeek get() = publishDate.publishWeek

val LocalDate.publishWeek get() = get(WeekFields.ISO.weekOfYear()).toString()

val Book.publishYear get() = publishDate.year
