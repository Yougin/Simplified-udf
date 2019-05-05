package com.blinkslabs.blinkist.android.challenge.data.book.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BookEntity
import io.reactivex.Observable

@Dao
interface BookDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertBook(book: BookEntity): Long

  @Query("SELECT * FROM BookEntity")
  fun fetchAllBooks(): Observable<List<BookEntity>>

}