package com.labs.biletskiy.android.data

import android.content.Context
import androidx.room.Room
import com.labs.biletskiy.android.data.book.datasource.BookRepository
import com.labs.biletskiy.android.data.book.datasource.BookRepositoryImpl
import com.labs.biletskiy.android.data.book.datasource.local.BookDao
import com.labs.biletskiy.android.data.book.datasource.remote.BooksApi
import com.labs.biletskiy.android.data.book.datasource.remote.MockBooksApi
import com.labs.biletskiy.android.data.database.BooksDatabase
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

  @Provides
  @Singleton
  fun providesBookRepository(repository: BookRepositoryImpl): BookRepository = repository

}