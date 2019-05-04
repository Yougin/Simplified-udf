package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import androidx.lifecycle.ViewModel
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooks
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import com.blinkslabs.blinkist.android.challenge.util.takeOnlyOnce
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class BooksViewModel @Inject constructor(
    private val getBooks: GetBooks,
    private val disposables: Disposables
) : ViewModel() {

  val viewState: Observable<BooksViewState> get() = _viewState
  private val _viewState = BehaviorSubject.create<BooksViewState>()
  private val intentsEmitter = PublishSubject.create<BooksIntent>()

  fun intents(intents: Observable<BooksIntent>): Disposable {
    subscribeToIntents()
    return intents.subscribe(
        { intentsEmitter.onNext(it) },
        { Timber.e(it, "Something went wrong processing intents") }
    )
  }

  private fun subscribeToIntents() {
    disposables += intentsEmitter
        .takeOnlyOnce(BooksIntent.InitialIntent::class.java)
        .subscribe {
          when (it) {
            is BooksIntent.InitialIntent, BooksIntent.ForceUpdateIntent -> fetchBooks()
          }
        }
  }

  private fun fetchBooks() {
    disposables += getBooks().toObservable()
        .map<BooksViewState> { BooksViewState.BooksFetched(it) }
        .startWith(BooksViewState.InFlight)
        .onErrorReturn { BooksViewState.Error(it) }
        .subscribeOn(BLSchedulers.io())
        .observeOn(BLSchedulers.main())
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


// TODO-eugene extract me from here
sealed class BooksViewState {
  object InFlight : BooksViewState()
  data class BooksFetched(val books: Books) : BooksViewState()
  data class Error(val throwable: Throwable) : BooksViewState()
}

sealed class BooksIntent {
  object InitialIntent : BooksIntent()
  object ForceUpdateIntent : BooksIntent()
}
