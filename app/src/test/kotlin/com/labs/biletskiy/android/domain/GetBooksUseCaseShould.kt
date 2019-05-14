package com.labs.biletskiy.android.domain

import arrow.core.Option
import com.google.common.truth.Truth.assertThat
import com.labs.biletskiy.android.data.book.datasource.BookRepository
import com.labs.biletskiy.android.domain.book.model.Book
import com.labs.biletskiy.android.domain.book.model.Books
import com.labs.biletskiy.android.domain.book.usecase.GetBooksUseCase
import com.labs.biletskiy.android.fake.FakeBookRepository
import com.labs.biletskiy.android.util.BLSchedulers
import com.labs.biletskiy.android.utils.getAllEvents
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

  @Test fun `emit Books upon subscription when some Books are persisted`() {
    whenever(bookRepository.getAllBooks()).thenReturn(Observable.just(Option(fakeBooks)))

    val observer = getBooks().test()

    assertThat(observer.values()[0]).isEqualTo(fakeBooks)
  }

  @Test fun `force fetch books from api if there are no persisted and emit the result`() {
    val fakeDao = PublishSubject.create<Option<Books>>()
    val fakeBookRepository = FakeBookRepository(fakeDao, fakeBooks)

    val useCase = GetBooksUseCase(fakeBookRepository)
    val observer = useCase().test()

    fakeDao.onNext(Option.empty())
    fakeDao.onNext(Option(fakeBooks)) // mimics Room's behavior
    observer.getAllEvents()

    assertThat(observer.values()[0]).isEqualTo(fakeBooks)
  }

  @Test fun `emits every time force fetch is invoked by the system`() {
    val fakeDao = PublishSubject.create<Option<Books>>()
    val fakeBookRepository = FakeBookRepository(fakeDao, fakeBooks)
    val useCase = GetBooksUseCase(fakeBookRepository)

    val observer = useCase().test()

    fakeDao.onNext(Option.empty())
    fakeDao.onNext(Option(fakeBooks))
    fakeBookRepository.fetchBooks() // Say, triggered by pull to refresh
    observer.getAllEvents()

    assertThat(observer.values().size).isEqualTo(3)
  }

}

private val fakeBooks = listOf(
    Book("1", "3", "5", LocalDate.of(2019, 1, 1), "7"),
    Book("2", "4", "6", LocalDate.of(2019, 1, 2), "8")
)