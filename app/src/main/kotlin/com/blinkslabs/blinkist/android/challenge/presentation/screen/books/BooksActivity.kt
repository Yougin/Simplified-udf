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
import com.blinkslabs.blinkist.android.challenge.util.showToast
import kotlinx.android.synthetic.main.activity_books.*
import javax.inject.Inject

class BooksActivity : AppCompatActivity() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  @Inject lateinit var viewContainer: ViewContainer
  private lateinit var viewModel: BooksViewModel
  private lateinit var recyclerAdapter: BookListRecyclerAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    app().component.getBooksComponent().inject(this)
    LayoutInflater.from(this).inflate(R.layout.activity_books,
                                      viewContainer.forActivity(this),
                                      true)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(BooksViewModel::class.java)

    setupRecyclerView()
    setupSwipeToRefresh()
  }

  private fun setupRecyclerView() {
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerAdapter = BookListRecyclerAdapter()
    recyclerView.adapter = recyclerAdapter
  }

  private fun setupSwipeToRefresh() {
    swipeRefreshView.setOnRefreshListener { }
  }


  private fun showBooks(books: Books) {
    recyclerAdapter.setItems(books)
    recyclerAdapter.notifyDataSetChanged()
    swipeRefreshView.isRefreshing = false
  }

  private fun showErrorLoadingData() {
    showToast(R.string.error_generic)
  }

  private fun app() = (application as BlinkistChallengeApplication)
}

