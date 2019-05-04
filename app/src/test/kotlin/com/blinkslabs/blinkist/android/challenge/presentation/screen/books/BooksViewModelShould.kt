@file:Suppress("unused")

package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooks
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import com.blinkslabs.blinkist.android.challenge.utils.getAllEvents
import com.google.common.truth.Truth.assertThat
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

// TODO-eugene rename other tests to 'should'
@RunWith(MockitoJUnitRunner::class)
class BooksViewModelShould {

  @Mock lateinit var getBooks: GetBooks
  @Mock lateinit var disposables: Disposables
  @InjectMocks lateinit var viewModel: BooksViewModel

  private lateinit var emitter: PublishSubject<BooksIntent>
  private lateinit var observer: TestObserver<BooksViewState>

  @Before fun setUp() {
    BLSchedulers.enableTesting()

    observer = viewModel.viewState.test()
    emitter = PublishSubject.create()
    viewModel.intents(emitter)

    givenASuccessfulBooksServiceCall(fakeBooks)
  }

  @Test fun `receive InFlight state upon subscription`() {
    emitter.onNext(BooksIntent.InitialIntent)

    val values = observer.values()
    observer.getAllEvents()

    assertThat(values[0]).isEqualTo(BooksViewState.InFlight)
  }

  @Test fun `receive BooksFetched state in response to its InitialIntent `(){
    emitter.onNext(BooksIntent.InitialIntent)

    val values = observer.values()
    observer.getAllEvents()

    assertThat(values.size).isEqualTo(2)
    assertThat(values[1]).isEqualTo(BooksViewState.BooksFetched(fakeBooks))
  }

  @Test fun `receive InitialIntent only once when configuration change occurs`(){
    emitter.onNext(BooksIntent.InitialIntent)
    observer.getAllEvents()

    observer.assertValueCount(2)

    emitter.onNext(BooksIntent.InitialIntent)
    observer.getAllEvents()

    observer.assertValueCount(2)
  }

  @Test fun `receive Error state when hard stop occurs`(){
    val throwable = RuntimeException("test")
    givenAnUnsuccessfulBooksServiceCall(throwable)

    emitter.onNext(BooksIntent.InitialIntent)

    val values = observer.values()
    observer.getAllEvents()

    assertThat(values.size).isEqualTo(2)
    assertThat(values[1]).isEqualTo(BooksViewState.Error(throwable))
  }

  private fun givenASuccessfulBooksServiceCall(result: Books) {
    whenever(getBooks()).thenReturn(Single.just(result))
  }

  private fun givenAnUnsuccessfulBooksServiceCall(exception: Throwable) {
    whenever(getBooks()).thenReturn(Single.error(exception))
  }

}


val fakeBooks: Books
  get() = listOf(Book("d241b2b", "Eat, Move, Sleep", "Tom Rath", LocalDate.of(2018, 7, 3), "https://images.blinkist.com/images/books/5694b3802f6827000700002a/3_4/640.jpg"),
                 Book("eea5ee1", "The Secret Life of Sleep", "Kat Duff", LocalDate.of(2018, 7, 2), "https://images.blinkist.com/images/books/56c1de12587c820007000063/3_4/640.jpg"),
                 Book("7e2401d", "The Sleep Revolution", "Arianna Huffington", LocalDate.of(2018, 6, 19), "https://images.blinkist.com/images/books/5776159b86883200034f4744/3_4/640.jpg"),
                 Book("03779ee", "Real Artists Don’t Starve", "Jeff Goins", LocalDate.of(2017, 12, 31), "https://images.blinkist.com/images/books/599817dbb238e10006a125cb/3_4/640.jpg"),
                 Book("e021f6c", "Hirntuning", "Dave Asprey", LocalDate.of(2018, 1, 1), "https://images.blinkist.com/images/books/5b284d46b238e1000787b43d/3_4/640.jpg"),
                 Book("8722651", "The Red Queen", "Matt Ridley", LocalDate.of(2018, 6, 17), "https://images.blinkist.com/images/books/58eb3b45a54bbb000464bab8/3_4/640.jpg"),
                 Book("2cb8609", "Inner Engineering", "Sadhguru Jaggi Vasudev", LocalDate.of(2018, 6, 18), "https://images.blinkist.com/images/books/59751e00b238e100057032bf/3_4/640.jpg"),
                 Book("b4388e4", "Feathers", "Thor Hanson", LocalDate.of(2018, 6, 18), "https://images.blinkist.com/images/books/59773cc1b238e10005084241/3_4/640.jpg"),
                 Book("1cdb347", "The Subtle Art of Not Giving a F*ck", "Mark Manson", LocalDate.of(2016, 7, 2), "https://images.blinkist.com/images/books/592933bbb238e10007b6b0a5/3_4/640.jpg"),
                 Book("a597717", "Bringing Up Bébé", "Pamela Druckerman", LocalDate.of(2016, 7, 3), "https://images.blinkist.com/images/books/57e6c3f0afd7bf0003b7052d/3_4/640.jpg"),
                 Book("99c1c39", "A Book With a Very Long Title, Veeeeeeeeeeeeeeeeery Long, Possibly the Most Long Title For a Book You've Ever Seen In Your Entire Life", "The Blinkist Android Team", LocalDate.of(2014, 1, 1), "https://images.blinkist.com/images/books/5575979e3935610007420000/3_4/640.jpg")
  )

// TODO-eugene delete me
val fakeBooks2: Books
  get() = listOf(Book("d241b2b", "Eat, Move, Sleep", "Tom Rath", LocalDate.of(2018, 7, 3), "https://images.blinkist.com/images/books/5694b3802f6827000700002a/3_4/640.jpg"),
                 Book("03779ee", "Real Artists Don’t Starve", "Jeff Goins", LocalDate.of(2017, 12, 31), "https://images.blinkist.com/images/books/599817dbb238e10006a125cb/3_4/640.jpg"),
                 Book("e021f6c", "Hirntuning", "Dave Asprey", LocalDate.of(2018, 1, 1), "https://images.blinkist.com/images/books/5b284d46b238e1000787b43d/3_4/640.jpg"),
                 Book("8722651", "The Red Queen", "Matt Ridley", LocalDate.of(2018, 6, 17), "https://images.blinkist.com/images/books/58eb3b45a54bbb000464bab8/3_4/640.jpg"),
                 Book("2cb8609", "Inner Engineering", "Sadhguru Jaggi Vasudev", LocalDate.of(2018, 6, 18), "https://images.blinkist.com/images/books/59751e00b238e100057032bf/3_4/640.jpg"),
                 Book("b4388e4", "Feathers", "Thor Hanson", LocalDate.of(2018, 6, 18), "https://images.blinkist.com/images/books/59773cc1b238e10005084241/3_4/640.jpg"),
                 Book("1cdb347", "The Subtle Art of Not Giving a F*ck", "Mark Manson", LocalDate.of(2016, 7, 2), "https://images.blinkist.com/images/books/592933bbb238e10007b6b0a5/3_4/640.jpg"),
                 Book("a597717", "Bringing Up Bébé", "Pamela Druckerman", LocalDate.of(2016, 7, 3), "https://images.blinkist.com/images/books/57e6c3f0afd7bf0003b7052d/3_4/640.jpg"),
                 Book("99c1c39", "A Book With a Very Long Title, Veeeeeeeeeeeeeeeeery Long, Possibly the Most Long Title For a Book You've Ever Seen In Your Entire Life", "The Blinkist Android Team", LocalDate.of(2014, 1, 1), "https://images.blinkist.com/images/books/5575979e3935610007420000/3_4/640.jpg")
  )