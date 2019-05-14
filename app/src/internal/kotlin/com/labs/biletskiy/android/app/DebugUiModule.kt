package com.labs.biletskiy.android.app

import com.labs.biletskiy.android.presentation.screen.books.rootview.ViewContainer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/** This Module has an alternative one -> UiModule.kt */
@Module
class DebugUiModule {

  @Provides @Singleton fun providesViewContainer(): ViewContainer = DebugViewContainer()

}