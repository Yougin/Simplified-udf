package com.blinkslabs.blinkist.android.challenge.data.api

import com.blinkslabs.blinkist.android.challenge.BlinkistChallengeApplication
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooks
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooksUseCase
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.BooksActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(injects = [BlinkistChallengeApplication::class, BooksActivity::class], library = true)
class BooksApiModule {

  @Provides
  fun providesBooksApi(booksApi: MockBooksApi): BooksApi = booksApi

  @Provides
  @Singleton
  fun providesBookService(useCase: GetBooksUseCase): GetBooks = useCase
}
