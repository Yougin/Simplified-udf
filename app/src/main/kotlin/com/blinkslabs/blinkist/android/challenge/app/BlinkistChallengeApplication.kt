package com.blinkslabs.blinkist.android.challenge.app

import android.app.Application
import com.blinkslabs.blinkist.android.challenge.data.DataModule
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins


class BlinkistChallengeApplication : Application() {

  open lateinit var component: AppComponent

  override fun onCreate() {
    super.onCreate()

    setRxJavaErrorHandler()
    buildObjectGraphAndInject()
  }

  private fun buildObjectGraphAndInject() {
    component =
        AppComponentProvider().get().dataModule(DataModule(this)).build().also { it.inject(this) }
  }

  private fun setRxJavaErrorHandler() {
    RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
  }

}

