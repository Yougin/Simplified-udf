package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import androidx.lifecycle.ViewModel
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooks
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.GetBooksUseCase
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.RefreshBooksByBruteForce
import com.blinkslabs.blinkist.android.challenge.domain.book.usecase.RefreshBooksByForce
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.IsGroupByWeeklyFeatureOn
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.IsGroupByWeeklyFeatureOnUseCase
import com.blinkslabs.blinkist.android.challenge.presentation.di.ScreenScope
import com.blinkslabs.blinkist.android.challenge.presentation.di.ViewModelKey
import com.blinkslabs.blinkist.android.challenge.presentation.di.ViewModelModule
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.BooksAdapter
import com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.BooksAdapterImpl
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [(BooksActivityModule::class)])
@ScreenScope
interface BooksActivityComponent {

  fun inject(activity: BooksActivity)
}

@Module(includes = [ViewModelModule::class])
abstract class BooksActivityModule {

  @Binds
  @IntoMap
  @ViewModelKey(BooksViewModel::class)
  abstract fun bindUserViewModel(mainViewModel: BooksViewModel): ViewModel

  @Binds
  @ScreenScope
  abstract fun bindGetBooksUseCase(useCase: GetBooksUseCase): GetBooks

  @Binds
  @ScreenScope
  abstract fun bindFeatureUseCase(
      useCase: IsGroupByWeeklyFeatureOnUseCase
  ): IsGroupByWeeklyFeatureOn

  @Binds
  @ScreenScope
  abstract fun bindBooksForceUpdate(useCase: RefreshBooksByBruteForce): RefreshBooksByForce

  @Binds
  @ScreenScope
  abstract fun bindAdapter(adapter: BooksAdapterImpl): BooksAdapter

}
