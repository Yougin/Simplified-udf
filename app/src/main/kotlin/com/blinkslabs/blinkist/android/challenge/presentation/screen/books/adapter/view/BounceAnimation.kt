package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.adapter.view

import android.view.View
import android.view.animation.AnimationUtils
import com.blinkslabs.blinkist.android.challenge.R

class MyBounceInterpolator(
    amplitude: Double,
    frequency: Double
) : android.view.animation.Interpolator {

  private var mAmplitude = 1.0
  private var mFrequency = 10.0

  init {
    mAmplitude = amplitude
    mFrequency = frequency
  }

  override fun getInterpolation(time: Float): Float {
    return (-1.0 * Math.pow(Math.E, -time / mAmplitude) *
        Math.cos(mFrequency * time) + 1).toFloat()
  }
}

fun bounceAnimation(): (View) -> Unit = {
  val bounce = AnimationUtils.loadAnimation(it.context, R.anim.bounce)
  bounce.interpolator = MyBounceInterpolator(0.1, 20.0)
  it.startAnimation(bounce)
}