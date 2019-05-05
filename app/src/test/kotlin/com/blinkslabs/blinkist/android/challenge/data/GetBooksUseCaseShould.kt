package com.blinkslabs.blinkist.android.challenge.data

import com.blinkslabs.blinkist.android.challenge.data.book.datasource.remote.BooksApi
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooksUseCase
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetBooksUseCaseShould {
  @Mock lateinit var booksApi: BooksApi

  @InjectMocks lateinit var getBooks: GetBooksUseCase

  private val mockBooks: Books = listOf(mock(), mock(), mock())

  @Before fun setUp() {
    BLSchedulers.enableTesting()
  }

  @After fun tearDown() {
    BLSchedulers.disableTesting()
  }

  @Test fun callBooksApiWhenGetBooksIsCalled() {
    givenASuccessfulBooksApiCall(mockBooks)
    getBooks()
    verify(booksApi).fetchAllBooks()
  }

  @Test fun returnBooksApiOutputWhenGetBooksIsSuccessful() {
    givenASuccessfulBooksApiCall(mockBooks)
    getBooks()
        .test()
        .assertValue(mockBooks)
  }

  @Test fun propagateExceptionWhenGetBooksIsUnsuccesful() {
    givenAnUnsuccessfulBooksApiCall(RuntimeException("test"))
    getBooks()
        .test()
        .assertError(RuntimeException::class.java)
  }

  private fun givenASuccessfulBooksApiCall(result: Books) {
    whenever(booksApi.fetchAllBooks()).thenReturn(Single.just(result))
  }

  private fun givenAnUnsuccessfulBooksApiCall(exception: Throwable) {
    whenever(booksApi.fetchAllBooks()).thenReturn(Single.error(exception))
  }
}
