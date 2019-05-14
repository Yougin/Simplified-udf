package com.labs.biletskiy.android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.labs.biletskiy.android.data.book.datasource.local.BookDao
import com.labs.biletskiy.android.data.book.entity.BookEntity
import com.labs.biletskiy.android.data.book.entity.BooksTypeConverters

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
@TypeConverters(BooksTypeConverters::class)
abstract class BooksDatabase : RoomDatabase() {

  abstract fun bookDao(): BookDao

}