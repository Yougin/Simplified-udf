package com.blinkslabs.blinkist.android.challenge.ui

import com.blinkslabs.blinkist.android.challenge.data.BooksService
import com.blinkslabs.blinkist.android.challenge.data.model.Book
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import com.nhaarman.mockitokotlin2.eq
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
class BooksPresenterShould {
    @Mock lateinit var booksService: BooksService
    @Mock lateinit var booksView: BooksView

    @InjectMocks lateinit var booksPresenter: BooksPresenter

    private val mockBooks: List<Book> = listOf(mock(), mock(), mock())

    @Before fun setUp() {
        BLSchedulers.enableTesting()
        booksPresenter.onCreate(booksView)
    }

    @After fun tearDown() {
        BLSchedulers.disableTesting()
    }

    @Test fun callGetBooksOnServiceWhenFetchBooksIsCalled() {
        givenASuccessfulBooksServiceCall(mockBooks)
        booksPresenter.fetchBooks()
        verify(booksService).getBooks()
    }

    @Test fun showBooksOnViewWhenFetchBooksIsSuccessful() {
        givenASuccessfulBooksServiceCall(mockBooks)
        booksPresenter.fetchBooks()
        verify(booksView).showBooks(eq(mockBooks))
    }

    @Test fun showErrorOnViewWhenFetchBooksIsUnsuccessful() {
        givenAnUnsuccessfulBooksServiceCall(RuntimeException("test"))
        booksPresenter.fetchBooks()
        verify(booksView).showErrorLoadingData()
    }

    private fun givenASuccessfulBooksServiceCall(result: List<Book>) {
        whenever(booksService.getBooks()).thenReturn(Single.just(result))
    }

    private fun givenAnUnsuccessfulBooksServiceCall(exception: Throwable) {
        whenever(booksService.getBooks()).thenReturn(Single.error(exception))
    }
}
