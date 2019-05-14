package com.labs.biletskiy.android.presentation.screen.books.grouper

import com.labs.biletskiy.android.domain.book.model.Books

fun groupByAlphabet(books: Books): GroupedBooks.ByAlphabet =
    books
        .filter { it.name.isNotBlank() }
        .sortedBy { it.name.first() }
        .groupBy { Title(it.name.first().toString().toUpperCase()) }
        .mapValues { _books -> _books.value.sortedBy { book -> book.publishDate } }
        .let { GroupedBooks.ByAlphabet(it) }