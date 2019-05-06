package com.blinkslabs.blinkist.android.challenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.blinkslabs.blinkist.android.challenge.data.book.datasource.local.BookDao
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BookEntity
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BooksTypeConverters

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
@TypeConverters(BooksTypeConverters::class)
abstract class BooksDatabase : RoomDatabase() {

  abstract fun bookDao(): BookDao

}