package com.labs.biletskiy.android.data.book.datasource.remote

import com.labs.biletskiy.android.domain.book.model.Book
import com.labs.biletskiy.android.domain.book.model.Books
import com.labs.biletskiy.android.util.BLSchedulers
import io.reactivex.Single
import org.threeten.bp.LocalDate
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MockBooksApi @Inject constructor() : BooksApi {

  override fun fetchAllBooks(): Single<Books> = Single.just(
      listOf(
          Book("d241b2b", "Eat, Move, Sleep", "Tom Rath", LocalDate.of(2018, 7, 3), "https://images.blinkist.com/images/books/5694b3802f6827000700002a/3_4/640.jpg"),
          Book("eea5ee1", "The Secret Life of Sleep", "Kat Duff", LocalDate.of(2018, 7, 2), "https://images.blinkist.com/images/books/56c1de12587c820007000063/3_4/640.jpg"),
          Book("7e2401d", "The Sleep Revolution", "Arianna Huffington", LocalDate.of(2018, 6, 19), "https://images.blinkist.com/images/books/5776159b86883200034f4744/3_4/640.jpg"),
          Book("03779ee", "Real Artists Don’t Starve", "Jeff Goins", LocalDate.of(2017, 12, 31), "https://images.blinkist.com/images/books/599817dbb238e10006a125cb/3_4/640.jpg"),
          Book("e021f6c", "Hirntuning", "Dave Asprey", LocalDate.of(2018, 1, 1), "https://images.blinkist.com/images/books/5b284d46b238e1000787b43d/3_4/640.jpg"),
          Book("8722651", "The Red Queen", "Matt Ridley", LocalDate.of(2018, 6, 17), "https://images.blinkist.com/images/books/58eb3b45a54bbb000464bab8/3_4/640.jpg"),
          Book("2cb8609", "Inner Engineering", "Sadhguru Jaggi Vasudev", LocalDate.of(2018, 6, 18), "https://images.blinkist.com/images/books/59751e00b238e100057032bf/3_4/640.jpg"),
          Book("b4388e4", "Feathers", "Thor Hanson", LocalDate.of(2018, 6, 18), "https://images.blinkist.com/images/books/59773cc1b238e10005084241/3_4/640.jpg"),
          Book("1cdb347", "The Subtle Art of Not Giving a F*ck", "Mark Manson", LocalDate.of(2016, 7, 2), "https://images.blinkist.com/images/books/592933bbb238e10007b6b0a5/3_4/640.jpg"),
          Book("a597717", "Bringing Up Bébé", "Pamela Druckerman", LocalDate.of(2016, 7, 3), "https://images.blinkist.com/images/books/57e6c3f0afd7bf0003b7052d/3_4/640.jpg"),
          Book("99c1c39", "A Book With a Very Long Title, Veeeeeeeeeeeeeeeeery Long, Possibly the Most Long Title For a Book You've Ever Seen In Your Entire Life", "The Blinkist Android Team", LocalDate.of(2014, 1, 1), "https://images.blinkist.com/images/books/5575979e3935610007420000/3_4/640.jpg")
      ))
      .delay(2, TimeUnit.SECONDS, BLSchedulers.computation())
}
