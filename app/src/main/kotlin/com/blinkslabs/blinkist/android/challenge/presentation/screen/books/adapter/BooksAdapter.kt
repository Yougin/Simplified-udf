package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.GroupByWeeklyFeature
import com.blinkslabs.blinkist.android.challenge.presentation.common.adapter.FlexibleAdapterImpl
import com.blinkslabs.blinkist.android.challenge.presentation.common.adapter.ItemDelegate
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.grouper.*
import javax.inject.Inject

interface BooksAdapter {

  fun setBooks(books: Books, weeklyFeature: GroupByWeeklyFeature)
}

class BooksAdapterImpl @Inject constructor( // List Item Delegates in here
) : FlexibleAdapterImpl(), BooksAdapter {

  override fun setBooks(books: Books, weeklyFeature: GroupByWeeklyFeature) {
    convertToAdapterData(books, weeklyFeature)
  }

}


fun convertToAdapterData(
    books: Books, weeklyFeature: GroupByWeeklyFeature
): List<*> {

  val groupedBooks = when (weeklyFeature) {
    GroupByWeeklyFeature.On -> groupByDate(books)
    GroupByWeeklyFeature.Off -> groupByAlphabet(books)
  }

  return when (groupedBooks) {
    is GroupedBooks.ByDate -> convert(groupedBooks)
    is GroupedBooks.ByAlphabet -> convert(groupedBooks)
  }
}

private fun convert(groupedBooks: GroupedBooks.ByDate): List<*> {
  val groupOfBooks: YearlyGroup = groupedBooks.group

  val list = mutableListOf<Any>()
  groupOfBooks.forEach { (year, weeklyGroup) ->
    list += YearTitle(year.value.toString())

    weeklyGroup.forEach { (title, books) ->
      list += WeekTitle(title.value)
      list.add(books.map { BookCard(it) })
    }
  }

  return list
}

fun convert(groupedBooks: GroupedBooks.ByAlphabet): List<*> {
  val groupOfBooks: AlphabeticGroup = groupedBooks.group

  val list = mutableListOf<Any>()
  groupOfBooks.forEach { (title, books) ->
    list += AlphabetTitle(title.value.toString())

    //    books.forEach { book -> list += }
  }

  return list
}


data class YearTitle(val text: String)
class YearTitleItemDelegate : ItemDelegate<YearTitle> {

  override val associatedDataType: Class<YearTitle>
    get() = TODO("not implemented")

  override fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): RecyclerView.ViewHolder {
    TODO("not implemented")
  }

  override fun bind(view: View, data: YearTitle) {
  }

}

data class WeekTitle(val text: String)
class WeekTitleItemDelegate : ItemDelegate<WeekTitle> {
  override val associatedDataType: Class<WeekTitle>
    get() = TODO("not implemented")

  override fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): RecyclerView.ViewHolder {
    TODO("implement")
  }

  override fun bind(view: View, data: WeekTitle) {
  }

}

data class BookCard(val book: Book)
class BookCardItemDelegate : ItemDelegate<BookCard> {
  override val associatedDataType: Class<BookCard>
    get() = TODO("not implemented")

  override fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): RecyclerView.ViewHolder {
    TODO("")
  }

  override fun bind(view: View, data: BookCard) {
  }

}
