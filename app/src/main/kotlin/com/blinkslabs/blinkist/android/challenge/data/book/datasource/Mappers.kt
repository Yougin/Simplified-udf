package com.blinkslabs.blinkist.android.challenge.data.book.datasource

import com.blinkslabs.blinkist.android.challenge.data.book.entity.BookEntity
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books

fun BookEntity.toBook(): Book =
    Book(this.id, this.name, this.author, this.publishDate, this.coverImageUrl)

fun List<BookEntity>.toBooks(): Books = this.map { it.toBook() }

fun Book.toEntity(): BookEntity =
    BookEntity(this.id, this.name, this.author, this.publishDate, this.coverImageUrl)