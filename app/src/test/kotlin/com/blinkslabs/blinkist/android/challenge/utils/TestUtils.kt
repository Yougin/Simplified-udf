package com.blinkslabs.blinkist.android.challenge.utils

import io.reactivex.observers.TestObserver
import java.util.concurrent.TimeUnit

fun <T> TestObserver<T>.getAllEvents() = this.awaitTerminalEvent(20, TimeUnit.MILLISECONDS)