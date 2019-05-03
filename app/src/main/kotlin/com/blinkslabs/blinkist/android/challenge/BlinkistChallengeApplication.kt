package com.blinkslabs.blinkist.android.challenge

import android.app.Application
import com.blinkslabs.blinkist.android.challenge.data.book.BooksApiModule
import dagger.ObjectGraph


class BlinkistChallengeApplication : Application() {

  private lateinit var objectGraph: ObjectGraph

  override fun onCreate() {
    super.onCreate()
    buildObjectGraphAndInject()
  }

  fun inject(instance: Any) {
    objectGraph.inject(instance)
  }

  private fun buildObjectGraphAndInject() {
    objectGraph = ObjectGraph.create(BooksApiModule())
    inject(this)
  }
}
