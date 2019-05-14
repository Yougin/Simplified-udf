package com.labs.biletskiy.android.presentation.screen.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.labs.biletskiy.android.challenge.R
import com.labs.biletskiy.android.domain.book.model.Book
import com.labs.biletskiy.android.domain.book.model.Books
import com.squareup.picasso.Picasso
import java.util.*

@Deprecated(replaceWith = ReplaceWith("Use FlexibleAdapter.kt"), message = "")
class BookListRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  val items = ArrayList<Book>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val v = LayoutInflater.from(parent.context)
        .inflate(R.layout.book_list_item, parent, false)
    return BookViewHolder(v)
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val bookHolder = holder as BookViewHolder
    val book = items[position]

    bookHolder.titleTextView.text = book.name
    bookHolder.authorTextView.text = book.author
    Picasso.get()
        .load(book.coverImageUrl)
        .into(bookHolder.coverImageView)
  }

  override fun getItemCount(): Int {
    return items.size
  }

  fun setItems(items: Books) {
    this.items.clear()
    this.items.addAll(items)
  }

  private inner class BookViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    var authorTextView: TextView = v.findViewById(R.id.author_text_view)
    var titleTextView: TextView = v.findViewById(R.id.title_text_view)
    var coverImageView: ImageView = v.findViewById(R.id.cover_image_view)
  }
}
