package com.blinkslabs.blinkist.android.challenge

import com.blinkslabs.blinkist.android.challenge.data.DataModule
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.BooksActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
  DataModule::class
])
interface AppComponent {
  fun inject(app: BlinkistChallengeApplication)

  fun getBooksComponent(): BooksActivityComponent
}