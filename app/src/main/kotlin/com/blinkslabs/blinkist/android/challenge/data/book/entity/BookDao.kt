package com.blinkslabs.blinkist.android.challenge.data.book.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Book
import com.blinkslabs.blinkist.android.challenge.domain.book.model.Books
import io.reactivex.Observable

@Dao
interface BookDao {

  @Insert
  fun insertBook(book: Book): String

  @Query("SELECT * FROM BookEntity")
  fun fetchAllBooks(): Observable<Books>

}