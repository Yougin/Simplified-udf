package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.delegate

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blinkslabs.blinkist.android.challenge.R
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.presentation.common.adapter.ItemDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.book_list_item.view.*

data class BookCard(val book: Book)

class BookCardItemDelegate : ItemDelegate<BookCard> {

  override val associatedDataType: Class<BookCard> get() = BookCard::class.java

  override fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): RecyclerView.ViewHolder =
      ItemDelegate.Holder(layoutInflater.inflate(R.layout.item_book_card, parent, false))

  override fun bind(view: View, data: BookCard) {
  }

}


class BooksCarousel @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyle: Int = 0
) : RecyclerView(context, attributeSet, defStyle) {

  private val carouselAdapter = CarouselAdapter(LayoutInflater.from(context))

  init {
    clipToPadding = false
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    adapter = carouselAdapter
  }

  var items: Books
    get() = carouselAdapter.items
    set(value) {
      carouselAdapter.items = value
      carouselAdapter.notifyDataSetChanged()
    }


  // TODO-eugene inner?
  private inner class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view)

  private inner class CarouselAdapter(
      val layoutInflater: LayoutInflater,
      var items: Books = emptyList()
  ) : RecyclerView.Adapter<CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder =
        layoutInflater.inflate(R.layout.book_list_item, parent, false)
            .let { CarouselViewHolder(it) }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: CarouselViewHolder,
        position: Int
    ) {
      val book = items[position]
      holder.itemView.let { view ->
        // Picasso is a hidden dependency, needs to be injected instead
        Picasso.get().load(book.coverImageUrl).into(view.cover_image_view)
        view.title_text_view.text = book.name
        view.author_text_view.text = book.author
      }
    }
  }
}
