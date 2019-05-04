package com.blinkslabs.blinkist.android.challenge.presentation.screen.books

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

interface Disposables {

  operator fun plusAssign(disposable: Disposable)

  fun clear()
}

class CompositeDisposables @Inject constructor() : Disposables {

  private val container = CompositeDisposable()

  override fun plusAssign(disposable: Disposable) {
    container.add(disposable)
  }

  override fun clear() {
    container.clear()
  }
}