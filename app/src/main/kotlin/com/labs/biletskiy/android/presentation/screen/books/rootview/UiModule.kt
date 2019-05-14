package com.labs.biletskiy.android.presentation.screen.books.rootview

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/** This Module has an alternative one -> [DebugUiModule.kt] */
@Module
class UiModule {

  @Provides @Singleton fun provideViewContainer(): ViewContainer = ViewContainer.Default

}

