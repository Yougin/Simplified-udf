package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import androidx.lifecycle.ViewModel
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooks
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.RefreshBooksByForce
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.GroupByWeeklyFeature
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.IsGroupByWeeklyFeatureOn
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import com.blinkslabs.blinkist.android.challenge.util.takeOnlyOnce
import io.reactivex.Observable
import io.reactivex.Observable.combineLatest
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class BooksViewModel @Inject constructor(
    private val getBooks: GetBooks,
    private val isGroupByWeeklyFeatureOn: IsGroupByWeeklyFeatureOn,
    private val refreshBooksByForce: RefreshBooksByForce,
    private val disposables: Disposables
) : ViewModel() {

  /** Subscribe for viewState changes */
  val viewState: Observable<BooksViewState> get() = _viewState
  private val _viewState = BehaviorSubject.create<BooksViewState>()

  private val dataSources: Observable<BooksViewState>
    get() = combineLatest(
        getBooks(),
        isGroupByWeeklyFeatureOn(),
        BiFunction { books: Books, weeklyFeature: GroupByWeeklyFeature ->
          BooksViewState.DataFetched(books, weeklyFeature)
        }
    )

  private val intentsEmitter = PublishSubject.create<BooksIntent>()
  fun intents(intents: Observable<BooksIntent>): Disposable {
    subscribeForUpcomingIntents
    return intents.subscribe(
        { intentsEmitter.onNext(it) },
        { Timber.e(it, "Something went wrong processing intents") }
    )
  }

  private val subscribeForUpcomingIntents by lazy {
    disposables += intentsEmitter
        .takeOnlyOnce(BooksIntent.InitialIntent::class.java)
        .doOnNext { Timber.d("----- Intent: ${it.javaClass.simpleName}") }
        .subscribe {
          when (it) {
            is BooksIntent.InitialIntent -> fetchData()
            is BooksIntent.ForceUpdateIntent -> forceUpdate()
          }
        }
  }

  private fun fetchData() {
    disposables += dataSources
        .startWith(BooksViewState.InFlight)
        .onErrorReturn { BooksViewState.Error(it) }
        .subscribeOn(BLSchedulers.io())
        .doOnNext { Timber.d("----- Result: ${it.javaClass.simpleName}") }
        .subscribe(
            { _viewState.onNext(it) },
            { Timber.e("Something went wrong fetching screen's data") }
        )
  }

  private fun forceUpdate() {
    disposables += refreshBooksByForce()
        .subscribeOn(BLSchedulers.io())
        .subscribe(
            { /** There's nothing to do in here, the state change happens only in one place */ },
            { Timber.e("Something went wrong while executing force update") })
  }

  override fun onCleared() {
    super.onCleared()
    disposables.clear()
  }
}
