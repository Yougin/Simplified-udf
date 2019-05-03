package com.blinkslabs.blinkist.android.challenge

import android.app.Application
import androidx.lifecycle.ViewModel
import com.blinkslabs.blinkist.android.challenge.data.DataModule
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject


class BlinkistChallengeApplication : Application() {

  open lateinit var component: AppComponent

  override fun onCreate() {
    super.onCreate()

    setRxJavaErrorHandler()
    buildObjectGraphAndInject()
  }

  private fun buildObjectGraphAndInject() {
    component =
        DaggerAppComponent.builder().dataModule(DataModule(this)).build().also { it.inject(this) }
  }

  private fun setRxJavaErrorHandler() {
    RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
  }

}


class BooksViewModel @Inject constructor() : ViewModel()
