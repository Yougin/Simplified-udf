package com.blinkslabs.blinkist.android.challenge.data.book.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface BookDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertBook(book: BookEntity): Long

  @Query("SELECT * FROM BookEntity")
  fun fetchAllBooks(): Observable<List<BookEntity>>

}