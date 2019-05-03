package com.blinkslabs.blinkist.android.challenge.app

import com.blinkslabs.blinkist.android.challenge.data.DataModule
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.BooksActivityComponent
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.rootview.UiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, UiModule::class])
interface AppComponent {

  fun inject(app: BlinkistChallengeApplication)

  fun getBooksComponent(): BooksActivityComponent
}