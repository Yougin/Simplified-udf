package com.blinkslabs.blinkist.android.challenge.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.blinkslabs.blinkist.android.challenge.data.book.datasource.local.BookDao
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BookEntity
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BooksTypeConverters

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
@TypeConverters(BooksTypeConverters::class)
abstract class BooksDatabase : RoomDatabase() {

  abstract fun bookDao(): BookDao

  companion object {
    private var INSTANCE: BooksDatabase? = null

    fun getInstance(context: Context): BooksDatabase? {
      if (INSTANCE == null) {
        synchronized(BooksDatabase::class) {
          INSTANCE = Room.databaseBuilder(context.applicationContext,
                                          BooksDatabase::class.java, "books.db").build()
        }
      }
      return INSTANCE
    }

    fun destroyInstance() {
      INSTANCE = null
    }
  }
}