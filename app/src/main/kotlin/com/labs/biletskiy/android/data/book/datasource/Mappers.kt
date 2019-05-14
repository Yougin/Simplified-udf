package com.labs.biletskiy.android.data.book.datasource

import com.labs.biletskiy.android.data.book.entity.BookEntity
import com.labs.biletskiy.android.domain.book.model.Book
import com.labs.biletskiy.android.domain.book.model.Books

fun BookEntity.toBook(): Book =
    Book(this.id, this.name, this.author, this.publishDate, this.coverImageUrl)

fun List<BookEntity>.toBooks(): Books = this.map { it.toBook() }

fun Book.toEntity(): BookEntity =
    BookEntity(this.id, this.name, this.author, this.publishDate, this.coverImageUrl)