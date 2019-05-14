package com.labs.biletskiy.android.domain

import com.labs.biletskiy.android.data.book.datasource.BookRepository
import com.labs.biletskiy.android.domain.book.usecase.RefreshBooksByBruteForce
import com.labs.biletskiy.android.util.BLSchedulers
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UpdateBooksByBruteForceShould {

  @Mock private lateinit var booksRepository: BookRepository

  @InjectMocks private lateinit var refreshBooks: RefreshBooksByBruteForce

  @Before fun setUp() {
    BLSchedulers.enableTesting()
  }

  @Test fun `interact with booksRepository upon subscription`() {
    refreshBooks().test()

    verify(booksRepository).fetchBooks()
  }
}