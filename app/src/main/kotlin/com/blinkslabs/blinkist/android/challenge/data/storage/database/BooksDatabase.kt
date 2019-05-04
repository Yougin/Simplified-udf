package com.blinkslabs.blinkist.android.challenge.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BookDao
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BooksDatabase : RoomDatabase() {

  abstract fun bookDao(): BookDao
}