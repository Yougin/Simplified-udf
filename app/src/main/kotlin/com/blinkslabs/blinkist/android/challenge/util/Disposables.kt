package com.blinkslabs.blinkist.android.challenge.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

interface Disposables {

  operator fun plusAssign(disposable: Disposable)

  fun clear()
}

class CompositeDisposables @Inject constructor() : Disposables {

  private val compositeDisposable = CompositeDisposable()

  override fun plusAssign(disposable: Disposable) {
    compositeDisposable.add(disposable)
  }

  override fun clear() {
    compositeDisposable.clear()
  }
}