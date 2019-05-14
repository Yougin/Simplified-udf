package com.labs.biletskiy.android.util

import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

  @Provides fun provideDisposables(disposables: CompositeDisposables): Disposables = disposables
}