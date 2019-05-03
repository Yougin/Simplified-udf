package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import com.blinkslabs.blinkist.android.challenge.presentation.di.ScreenScope
import dagger.Subcomponent

@Subcomponent(modules = [(BooksActivityModule::class)])
@ScreenScope
interface BooksActivityComponent {
  fun inject(activity: BooksActivity)
}