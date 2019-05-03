package com.blinkslabs.blinkist.android.challenge.data

import com.blinkslabs.blinkist.android.challenge.data.api.BooksApi
import com.blinkslabs.blinkist.android.challenge.domain.book.Books
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
class BooksServiceShould {
    @Mock lateinit var booksApi: BooksApi

    @InjectMocks lateinit var booksService: BooksService

    private val mockBooks: Books = listOf(mock(), mock(), mock())

    @Before fun setUp() {
        BLSchedulers.enableTesting()
    }

    @After fun tearDown() {
        BLSchedulers.disableTesting()
    }

    @Test fun callBooksApiWhenGetBooksIsCalled() {
        givenASuccessfulBooksApiCall(mockBooks)
        booksService.getBooks()
        verify(booksApi).getAllBooks()
    }

    @Test fun returnBooksApiOutputWhenGetBooksIsSuccessful() {
        givenASuccessfulBooksApiCall(mockBooks)
        booksService
            .getBooks()
            .test()
            .assertValue(mockBooks)
    }

    @Test fun propagateExceptionWhenGetBooksIsUnsuccesful() {
        givenAnUnsuccessfulBooksApiCall(RuntimeException("test"))
        booksService
            .getBooks()
            .test()
            .assertError(RuntimeException::class.java)
    }

    private fun givenASuccessfulBooksApiCall(result: Books) {
        whenever(booksApi.getAllBooks()).thenReturn(Single.just(result))
    }

    private fun givenAnUnsuccessfulBooksApiCall(exception: Throwable) {
        whenever(booksApi.getAllBooks()).thenReturn(Single.error(exception))
    }
}
