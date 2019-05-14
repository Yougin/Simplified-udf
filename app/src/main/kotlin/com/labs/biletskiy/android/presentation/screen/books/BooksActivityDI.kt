package com.labs.biletskiy.android.presentation.screen.books

import androidx.lifecycle.ViewModel
import com.labs.biletskiy.android.domain.book.usecase.GetBooks
import com.labs.biletskiy.android.domain.book.usecase.GetBooksUseCase
import com.labs.biletskiy.android.domain.book.usecase.RefreshBooksByBruteForce
import com.labs.biletskiy.android.domain.book.usecase.RefreshBooksByForce
import com.labs.biletskiy.android.domain.featurewitch.IsGroupByWeeklyFeatureOn
import com.labs.biletskiy.android.domain.featurewitch.IsGroupByWeeklyFeatureOnUseCase
import com.labs.biletskiy.android.presentation.di.ScreenScope
import com.labs.biletskiy.android.presentation.di.ViewModelKey
import com.labs.biletskiy.android.presentation.di.ViewModelModule
import com.labs.biletskiy.android.presentation.screen.books.adapter.BooksAdapter
import com.labs.biletskiy.android.presentation.screen.books.adapter.BooksAdapterImpl
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
