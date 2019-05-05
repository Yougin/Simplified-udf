package com.blinkslabs.blinkist.android.challenge.data

import arrow.core.Option
import com.blinkslabs.blinkist.android.challenge.data.book.datasource.BookRepository
import com.blinkslabs.blinkist.android.challenge.data.fake.FakeBookRepository
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooksUseCase
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import com.blinkslabs.blinkist.android.challenge.utils.getAllEvents
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.threeten.bp.LocalDate

@RunWith(MockitoJUnitRunner::class)
class GetBooksUseCaseShould {

  @Mock private lateinit var bookRepository: BookRepository

  @InjectMocks lateinit var getBooks: GetBooksUseCase

  @Before fun setUp() {
    BLSchedulers.enableTesting()
  }

  @Test fun `emit Books when some are persisted`() {
    whenever(bookRepository.getAllBooks()).thenReturn(Observable.just(Option(fakeBooks)))

    val observer = getBooks().test()

    assertThat(observer.values()[0]).isEqualTo(fakeBooks)
  }

  @Test fun `force fetch books from api if there are no persisted and emit the result`() {
    val bookEmitter = PublishSubject.create<Option<Books>>()
    val fakeBookRepository = FakeBookRepository(bookEmitter, fakeBooks)

    val useCase = GetBooksUseCase(fakeBookRepository)
    val observer = useCase().test()

    bookEmitter.onNext(Option.empty())
    bookEmitter.onNext(Option(fakeBooks))
    observer.getAllEvents()

    assertThat(observer.values()[0]).isEqualTo(fakeBooks)
  }

  @Test fun `emits every time force fetch is forced by the system`() {
    val bookEmitter = PublishSubject.create<Option<Books>>()
    val fakeBookRepository = FakeBookRepository(bookEmitter, fakeBooks)

    val useCase = GetBooksUseCase(fakeBookRepository)
    val observer = useCase().test()

    bookEmitter.onNext(Option.empty())
    bookEmitter.onNext(Option(fakeBooks))
    fakeBookRepository.fetchBooks() // say, triggered by pull to refresh
    observer.getAllEvents()

    assertThat(observer.values().size).isEqualTo(3)

  }

}

private val fakeBooks = listOf(
    Book("1", "3", "5", LocalDate.of(2019, 1, 1), "7"),
    Book("2", "4", "6", LocalDate.of(2019, 1, 2), "8")
)