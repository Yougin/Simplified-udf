package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import androidx.lifecycle.ViewModel
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooks
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import com.blinkslabs.blinkist.android.challenge.util.takeOnlyOnce
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject


class BooksViewModel @Inject constructor(
    private val getBooks: GetBooks,
    private val isGroupByWeeklyFeatureOn: IsGroupByWeeklyFeatureOn,
    private val disposables: Disposables
) : ViewModel() {

  val viewState: Observable<BooksViewState> get() = _viewState
  private val _viewState = BehaviorSubject.create<BooksViewState>()
  private val intentsEmitter = PublishSubject.create<BooksIntent>()

  fun intents(intents: Observable<BooksIntent>): Disposable {
    subscribeForUpcomingEvents()
    return intents.subscribe(
        { intentsEmitter.onNext(it) },
        { Timber.e(it, "Something went wrong processing intents") }
    )
  }

  private fun subscribeForUpcomingEvents() {
    disposables += intentsEmitter
        .takeOnlyOnce(BooksIntent.InitialIntent::class.java)
        .doOnNext { Timber.d("----- Intent: ${it.javaClass.simpleName}") }
        .subscribe {
          when (it) {
            is BooksIntent.InitialIntent, BooksIntent.ForceUpdateIntent -> fetchData()
          }
        }
  }

  private fun fetchData() {
    val getBooks = getBooks()
        .doOnNext { Timber.d("----- Get Books emits $it") }

    val isFeatureOn = isGroupByWeeklyFeatureOn()
        .doOnNext { Timber.d("----- Feature Switch emits $it") }

    val dataSources =
        Observable.combineLatest(getBooks, isFeatureOn,
                                 BiFunction { a: Books, b: Boolean -> Pair(a, b) })

    // TODO-eugene I don't need to declare main thread in here, do it in the View
    disposables += dataSources
        .map<BooksViewState> { BooksViewState.DataFetched(it.first, it.second) }
        .startWith(BooksViewState.InFlight)
        .onErrorReturn { BooksViewState.Error(it) }
        .subscribeOn(BLSchedulers.io())
        .doOnNext { Timber.d("----- Result: ${it.javaClass.simpleName}") }
        .subscribe({ _viewState.onNext(it) },
                   { Timber.e("Something went wrong fetching books") }
        )
  }

  override fun onCleared() {
    super.onCleared()
    disposables.clear()
  }

}


// TODO-eugene extract us from here
sealed class BooksViewState {
  object InFlight : BooksViewState()
  data class DataFetched(val books: Books, val isFeatureOn: Boolean) : BooksViewState()
  data class Error(val throwable: Throwable) : BooksViewState()
}

sealed class BooksIntent {
  object InitialIntent : BooksIntent()
  object ForceUpdateIntent : BooksIntent()
}


interface IsGroupByWeeklyFeatureOn {

  operator fun invoke(): Observable<Boolean>
}

class IsGroupByWeeklyFeatureOnUseCase @Inject constructor() : IsGroupByWeeklyFeatureOn {

  override fun invoke(): Observable<Boolean> = Observable.just(true)
}