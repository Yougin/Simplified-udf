package com.blinkslabs.blinkist.android.challenge.app

class AppComponentProvider {

  fun get() = DaggerAppComponent.builder()
}