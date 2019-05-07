package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blinkslabs.blinkist.android.challenge.R
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.book_list_item.view.*


class BooksCarouselView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyle: Int = 0
) : RecyclerView(context, attributeSet, defStyle) {

  // In real project this one would be injected
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


  private class CarouselViewHolder(view: View) : ViewHolder(view)

  private class CarouselAdapter(
      val layoutInflater: LayoutInflater,
      var items: Books = emptyList()
  ) : Adapter<CarouselViewHolder>() {

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
        view.setOnClickListener(bounceAnimation())
      }
    }
  }
}