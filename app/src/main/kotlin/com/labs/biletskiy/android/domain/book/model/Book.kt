package com.labs.biletskiy.android.domain.book.model

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

val Book.publishYear get() = publishDate.year

val Book.publishWeek get() = publishDate.get(WeekFields.ISO.weekOfYear()).toString()
