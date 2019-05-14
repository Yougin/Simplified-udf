package com.labs.biletskiy.android.data.book.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.labs.biletskiy.android.data.book.entity.BookEntity
import io.reactivex.Observable

@Dao
interface BookDao {

  @Query("SELECT * FROM BookEntity")
  fun getAllBooks(): Observable<List<BookEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertBook(book: BookEntity): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(books: List<BookEntity>)

  @Query("DELETE FROM BookEntity")
  fun deleteAllBooks()

}