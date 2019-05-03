package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blinkslabs.blinkist.android.challenge.BlinkistChallengeApplication
import com.blinkslabs.blinkist.android.challenge.BooksViewModel
import com.blinkslabs.blinkist.android.challenge.R
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.util.showToast
import kotlinx.android.synthetic.main.activity_books.*
import javax.inject.Inject

class BooksActivity : AppCompatActivity(), BooksView {

  @Inject
  lateinit var presenter: BooksPresenter

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: BooksViewModel

  private lateinit var recyclerAdapter: BookListRecyclerAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_books)

    app().component.getBooksComponent().inject(this)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(BooksViewModel::class.java)

    setupRecyclerView()
    setupSwipeToRefresh()

    presenter.onCreate(this)
    presenter.fetchBooks()
  }

  private fun setupRecyclerView() {
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerAdapter = BookListRecyclerAdapter()
    recyclerView.adapter = recyclerAdapter
  }

  private fun setupSwipeToRefresh() {
    swipeRefreshView.setOnRefreshListener { presenter.fetchBooks() }
  }


  override fun showBooks(books: Books) {
    recyclerAdapter.setItems(books)
    recyclerAdapter.notifyDataSetChanged()
    swipeRefreshView.isRefreshing = false
  }

  override fun showErrorLoadingData() {
    showToast(R.string.error_generic)
  }

  override fun onDestroy() {
    presenter.onDestroy()
    super.onDestroy()
  }

  private fun app() = (application as BlinkistChallengeApplication)
}

