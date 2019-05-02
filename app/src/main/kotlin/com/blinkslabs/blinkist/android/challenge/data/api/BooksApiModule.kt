package com.blinkslabs.blinkist.android.challenge.data.api

import com.blinkslabs.blinkist.android.challenge.BlinkistChallengeApplication
import com.blinkslabs.blinkist.android.challenge.data.BooksService
import com.blinkslabs.blinkist.android.challenge.ui.BooksActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(injects = [BlinkistChallengeApplication::class, BooksActivity::class], library = true)
class BooksApiModule {

    @Provides
    fun providesBooksApi(): BooksApi = MockBooksApi()

    @Provides
    @Singleton
    fun providesBookService(booksApi: BooksApi): BooksService = BooksService(booksApi)
}
