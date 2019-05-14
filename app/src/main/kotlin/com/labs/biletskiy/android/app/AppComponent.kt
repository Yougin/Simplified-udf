package com.labs.biletskiy.android.app

import com.labs.biletskiy.android.data.DataModule
import com.labs.biletskiy.android.presentation.screen.books.BooksActivityComponent
import com.labs.biletskiy.android.presentation.screen.books.rootview.UiModule
import com.labs.biletskiy.android.util.UtilsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, UiModule::class, UtilsModule::class])
interface AppComponent {

  fun inject(app: App)

  fun getBooksComponent(): BooksActivityComponent
}