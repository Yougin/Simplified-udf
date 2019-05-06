package com.blinkslabs.blinkist.android.challenge.app

import android.app.Application
import com.blinkslabs.blinkist.android.challenge.data.DataModule
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber


class BlinkistChallengeApplication : Application() {

  lateinit var component: AppComponent

  override fun onCreate() {
    super.onCreate()

    buildObjectGraphAndInject()
    setupTimber()
    setRxJavaErrorHandler()
  }

  private fun buildObjectGraphAndInject() {
    component =
        getAppComponent().dataModule(DataModule(this)).build().also { it.inject(this) }
  }

  private fun setRxJavaErrorHandler() {
    RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
  }

  private fun setupTimber() {
    Timber.plant(Timber.DebugTree())
  }

}

