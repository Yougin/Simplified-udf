package com.blinkslabs.blinkist.android.challenge.data

import android.content.Context
import androidx.room.Room
import com.blinkslabs.blinkist.android.challenge.data.book.BooksApi
import com.blinkslabs.blinkist.android.challenge.data.book.MockBooksApi
import com.blinkslabs.blinkist.android.challenge.data.book.entity.BookDao
import com.blinkslabs.blinkist.android.challenge.data.database.BooksDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val context: Context) {

  @Provides
  @Singleton
  fun providesBooksApi(booksApi: MockBooksApi): BooksApi = booksApi

  @Provides
  @Singleton
  fun providesRoomDatabase(): BooksDatabase =
      Room.databaseBuilder(context, BooksDatabase::class.java, "books_db").build()

  @Provides
  @Singleton
  fun providesBooksDao(booksDatabase: BooksDatabase): BookDao = booksDatabase.bookDao()

}