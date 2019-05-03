package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import androidx.lifecycle.ViewModel
import com.blinkslabs.blinkist.android.challenge.BooksViewModel
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooks
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooksUseCase
import com.blinkslabs.blinkist.android.challenge.presentation.di.ScreenScope
import com.blinkslabs.blinkist.android.challenge.presentation.di.ViewModelKey
import com.blinkslabs.blinkist.android.challenge.presentation.di.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class BooksActivityModule {

  @Binds
  @IntoMap
  @ViewModelKey(BooksViewModel::class)
  abstract fun bindUserViewModel(mainViewModel: BooksViewModel): ViewModel

  @Binds
  @ScreenScope
  abstract fun providesMainUseCase(getBooks: GetBooksUseCase): GetBooks

}
