package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blinkslabs.blinkist.android.challenge.R
import com.blinkslabs.blinkist.android.challenge.app.BlinkistChallengeApplication
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.rootview.ViewContainer
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import com.blinkslabs.blinkist.android.challenge.util.showToast
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.Observable
import io.reactivex.Observable.merge
import kotlinx.android.synthetic.main.activity_books.*
import timber.log.Timber
import javax.inject.Inject

class BooksActivity : AppCompatActivity() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  @Inject lateinit var viewContainer: ViewContainer
  @Inject lateinit var disposables: Disposables

  private lateinit var viewModel: BooksViewModel
  // TODO-eugene remove
  private lateinit var recyclerAdapter: BookListRecyclerAdapter

  private val intents: Observable<BooksIntent>
    get() = merge(
        Observable.just(BooksIntent.InitialIntent),
        swipeRefreshView.refreshes().map { BooksIntent.ForceUpdateIntent }
    )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    app().component.getBooksComponent().inject(this)
    LayoutInflater.from(this).inflate(R.layout.activity_books,
                                      viewContainer.forActivity(this),
                                      true)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(BooksViewModel::class.java)

    setupRecyclerView()
  }

  override fun onResume() {
    super.onResume()

    announceYourIntentions()
    subscribeToViewStateChanges()
  }

  private fun announceYourIntentions() {
    disposables += viewModel.intents(intents)
  }

  private fun subscribeToViewStateChanges() {
    disposables += viewModel.viewState
        .observeOn(BLSchedulers.main())
        .subscribe { renderState(it) }
  }

  private fun renderState(viewState: BooksViewState) {
    when (viewState) {
      is BooksViewState.InFlight -> {
        swipeRefreshView.isRefreshing = true
        Timber.d("----- Render InFlight: $viewState")
      }
      is BooksViewState.DataFetched -> {
        swipeRefreshView.isRefreshing = false
        Timber.d("----- Render DataFetched: $viewState")
      }
      is BooksViewState.Error -> {
        swipeRefreshView.isRefreshing = false
        showErrorLoadingData()
        Timber.e("----- Render Error $viewState")
      }
    }
  }

  override fun onStop() {
    super.onStop()
    disposables.clear()
  }

  private fun setupRecyclerView() {
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerAdapter = BookListRecyclerAdapter()
    recyclerView.adapter = recyclerAdapter
  }


  private fun showBooks(books: Books) {
    recyclerAdapter.setItems(books)
    recyclerAdapter.notifyDataSetChanged()
  }

  private fun showErrorLoadingData() {
    showToast(R.string.error_generic)
  }

  private fun app() = (application as BlinkistChallengeApplication)
}
