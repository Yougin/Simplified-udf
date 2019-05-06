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
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.GroupByWeeklyFeature
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.BooksAdapterImpl
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.rootview.ViewContainer
import com.blinkslabs.blinkist.android.challenge.util.BLSchedulers
import com.blinkslabs.blinkist.android.challenge.util.Disposables
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

  // This one should be injected the very same way other dependencies are being injected, I'm
  // just running out of time
  private var adapter = BooksAdapterImpl()

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

  private fun setupRecyclerView() {
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter.asRecyclerAdapter()
  }

  override fun onResume() {
    super.onResume()

    announceViewIntentions()
    subscribeToViewStateChanges()
  }

  private fun announceViewIntentions() {
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
        showBooks(viewState.books, viewState.weeklyFeature)
        Timber.d("----- Render DataFetched: $viewState")
      }
      is BooksViewState.Error -> {
        swipeRefreshView.isRefreshing = false
        showErrorLoadingData()
        Timber.e("----- Render Error $viewState")
      }
    }
  }

  private fun showBooks(
      books: Books,
      weeklyFeature: GroupByWeeklyFeature
  ) {
    adapter.setBooks(books, weeklyFeature)
    adapter.notifyDataSetChanged()
  }

  private fun showErrorLoadingData() {
    showToast(R.string.error_generic)
  }

  override fun onStop() {
    super.onStop()
    disposables.clear()
  }

  private fun app() = (application as BlinkistChallengeApplication)
}
