package com.blinkslabs.blinkist.android.challenge.data.book.datasource

import arrow.core.Option
import arrow.core.Some
import com.blinkslabs.blinkist.android.challenge.data.book.datasource.local.BookDao
import com.blinkslabs.blinkist.android.challenge.data.book.datasource.remote.BooksApi
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BookEntity
import com.blinkslabs.blinkist.android.challenge.data.fake.FakeDao
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.threeten.bp.LocalDate

@RunWith(MockitoJUnitRunner::class)
class BookRepositoryImpShould {

  @Mock private lateinit var bookDao: BookDao
  @Mock private lateinit var booksApi: BooksApi

  @InjectMocks lateinit var repository: BookRepositoryImpl

  private lateinit var bookDaoEmitter: PublishSubject<List<BookEntity>>
  private lateinit var bookApiEmitter: PublishSubject<Books>
  private lateinit var observer: TestObserver<Option<Books>>

  @Before fun setUp() {
    BLSchedulers.enableTesting()

    initBookDaoEmitter()
    initBookApiEmitter()

    observer = repository.getAllBooks().test()
  }

  @Test fun `emit persisted books upon subscription when there are some`() {
    bookDaoEmitter.onNext(fakeEntities)

    assertThat(observer.values()[0]).isEqualTo(Option(fakeBooks))
  }

  @Test fun `emit None if no persisted books`() {
    bookDaoEmitter.onNext(emptyList())

    assertThat(observer.values()[0]).isEqualTo(Option.empty<Books>())
  }

  @Test fun `emit books fetched from api upon subscription`() {
    val repository = BookRepositoryImpl(FakeDao(), booksApi)
    whenever(booksApi.fetchAllBooks()).thenReturn(Single.just(fakeBooks))

    val observer = repository.getAllBooks().test()
    repository.fetchBooks().test()

    assertThat(observer.values()[0]).isEqualTo(Some(fakeBooks))
  }

  @Test fun `delete all the books before fetching new ones`() {
    repository.fetchBooks().test()

    verify(bookDao).deleteAllBooks()
  }

  private fun initBookDaoEmitter() {
    with(PublishSubject.create<List<BookEntity>>()) {
      bookDaoEmitter = this
      whenever(bookDao.getAllBooks()).thenReturn(this)
    }
  }

  private fun initBookApiEmitter() {
    with(PublishSubject.create<Books>()) {
      bookApiEmitter = this
      whenever(booksApi.fetchAllBooks()).thenReturn(this.singleOrError())
    }
  }
}

private val fakeEntities = listOf(
    BookEntity("1", "3", "5", LocalDate.of(2019, 1, 1), "7"),
    BookEntity("2", "4", "6", LocalDate.of(2019, 1, 2), "8")
)

private val fakeBooks = listOf(
    Book("1", "3", "5", LocalDate.of(2019, 1, 1), "7"),
    Book("2", "4", "6", LocalDate.of(2019, 1, 2), "8")
)