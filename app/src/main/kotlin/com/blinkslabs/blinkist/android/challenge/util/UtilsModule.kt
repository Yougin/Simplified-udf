package com.blinkslabs.blinkist.android.challenge.util

import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.CompositeDisposables
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.Disposables
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

  @Provides fun provideDisposables(disposables: CompositeDisposables): Disposables = disposables
}