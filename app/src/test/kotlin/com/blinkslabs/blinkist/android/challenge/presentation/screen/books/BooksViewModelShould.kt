@file:Suppress("unused")

package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooks
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.UpdateBooksByForce
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.GroupByWeeklyFeature
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.IsGroupByWeeklyFeatureOn
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import com.blinkslabs.blinkist.android.challenge.utils.getAllEvents
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
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
class BooksViewModelShould {

  @Mock private lateinit var getBooks: GetBooks
  @Mock private lateinit var isGroupByWeeklyFeatureOn: IsGroupByWeeklyFeatureOn
  @Mock private lateinit var updateBooksByForce: UpdateBooksByForce
  @Mock private lateinit var disposables: Disposables

  @InjectMocks lateinit var viewModel: BooksViewModel

  private lateinit var getBooksEmitter: PublishSubject<Books>
  private lateinit var weeklyFeatureEmitter: PublishSubject<GroupByWeeklyFeature>
  private lateinit var viewEmitter: PublishSubject<BooksIntent>

  private lateinit var observer: TestObserver<BooksViewState>

  @Before fun setUp() {
    BLSchedulers.enableTesting()

    initViewEmitter()
    initGetBooksEmitter()
    initWeeklyFeatureEmitter()

    observer = viewModel.viewState.test()

    viewEmits(BooksIntent.InitialIntent)
    getBooksEmits()
  }

  @Test fun `receive InFlight state upon subscription`() {
    viewEmits(BooksIntent.InitialIntent)

    val values = observer.values()
    observer.getAllEvents()

    assertThat(values[0]).isEqualTo(BooksViewState.InFlight)
  }

  @Test fun `emit DataFetched state to View in response to InitialIntent from View`() {
    viewEmits(BooksIntent.InitialIntent)
    val expectedValue = GroupByWeeklyFeature.On
    weeklyFeatureSwitchEmits(expectedValue)

    val values = observer.values()
    observer.getAllEvents()

    assertThat(values.size).isEqualTo(2)
    assertThat(values[1]).isEqualTo(BooksViewState.DataFetched(fakeBooks, expectedValue))
  }

  @Test fun `observe changes from all sources and emit new state to View on each data change`() {
    weeklyFeatureSwitchEmits()
    observer.getAllEvents()

    assertThat(observer.values().size).isEqualTo(2)

    weeklyFeatureSwitchEmits(GroupByWeeklyFeature.Off)
    observer.getAllEvents()

    assertThat(observer.values().size).isEqualTo(3)

    getBooksEmits()
    observer.getAllEvents()

    assertThat(observer.values().size).isEqualTo(4)
  }

  // TODO-eugene should stay alive after conf change
  @Test fun `not react to InitialIntent received after configuration change`() {
    weeklyFeatureSwitchEmits()
    observer.getAllEvents()

    observer.assertValueCount(2)

    // Configuration change
    observer.dispose()

    observer = viewModel.viewState.test()
    val newEmitter = PublishSubject.create<BooksIntent>() // new View instance
    viewModel.intents(newEmitter)

    newEmitter.onNext(BooksIntent.InitialIntent)
    observer.getAllEvents()

    observer.assertValueCount(1)
  }

  @Test fun `emit Error state to View when hard stop occurs`() {
    viewEmits(BooksIntent.InitialIntent)
    getBooksEmits(withError = true)

    val values = observer.values()
    observer.getAllEvents()

    assertThat(values.size).isEqualTo(2)
    assertThat(values[1]).isEqualTo(BooksViewState.Error(TestException))
  }

  @Test fun `interact with updateBooksByForce use case on ForceUpdate intent`() {
    weeklyFeatureSwitchEmits()
    whenever(updateBooksByForce()).thenReturn(Completable.complete())
    viewEmits(BooksIntent.InitialIntent)
    viewEmits(BooksIntent.ForceUpdateIntent)

    verify(updateBooksByForce).invoke()
  }

  private fun initWeeklyFeatureEmitter() {
    with(PublishSubject.create<GroupByWeeklyFeature>()) {
      weeklyFeatureEmitter = this
      whenever(isGroupByWeeklyFeatureOn()).thenReturn(this)
    }
  }

  private fun initGetBooksEmitter() {
    with(PublishSubject.create<Books>()) {
      getBooksEmitter = this
      whenever(getBooks()).thenReturn(this)
    }
  }

  private fun initViewEmitter() {
    with(PublishSubject.create<BooksIntent>()) {
      viewEmitter = this
      viewModel.intents(this)
    }
  }

  private fun getBooksEmits(books: Books = fakeBooks, withError: Boolean = false) {
    if (withError) getBooksEmitter.onError(TestException)
    else getBooksEmitter.onNext(books)
  }

  private fun weeklyFeatureSwitchEmits(value: GroupByWeeklyFeature = GroupByWeeklyFeature.On) {
    weeklyFeatureEmitter.onNext(value)
  }

  private fun viewEmits(intent: BooksIntent) {
    viewEmitter.onNext(intent)
  }

}

private object TestException : RuntimeException()

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