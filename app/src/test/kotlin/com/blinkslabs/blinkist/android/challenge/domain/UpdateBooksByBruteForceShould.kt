package com.blinkslabs.blinkist.android.challenge.domain

import com.blinkslabs.blinkist.android.challenge.data.book.datasource.BookRepository
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.RefreshBooksByBruteForce
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
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