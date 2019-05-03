package com.blinkslabs.blinkist.android.challenge.data

import android.content.Context
import com.blinkslabs.blinkist.android.challenge.data.book.BooksApi
import com.blinkslabs.blinkist.android.challenge.data.book.MockBooksApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val app: Context) {

  @Provides
  @Singleton
  fun providesBooksApi(booksApi: MockBooksApi): BooksApi = booksApi

}