package com.blinkslabs.blinkist.android.challenge.app

import com.blinkslabs.blinkist.android.challenge.DaggerAppComponent

class AppComponentProvider {

  fun get() = DaggerAppComponent.builder()
}