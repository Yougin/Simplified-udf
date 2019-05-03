package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooks
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject


class BooksPresenter @Inject constructor(private val getBooks: GetBooks) {

  private lateinit var view: BooksView

  private val subscriptions = CompositeDisposable()

  fun onCreate(view: BooksView) {
    this.view = view
  }

  fun fetchBooks() {
    subscriptions.add(getBooks()
                          .subscribeOn(BLSchedulers.io())
                          .observeOn(BLSchedulers.main())
                          .subscribe({ books ->
                                       view.showBooks(books)
                                     }, { throwable ->
                                       view.showErrorLoadingData()
                                       Timber.e(throwable, "while loading data")
                                     }))
  }

  fun onDestroy() {
    subscriptions.clear()
  }
}
