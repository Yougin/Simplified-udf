package com.blinkslabs.blinkist.android.challenge.app

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.blinkslabs.blinkist.android.challenge.data.featureswitch.FeatureRepository
import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.GroupByWeeklyFeature
import com.jakewharton.rxbinding3.widget.checkedChanges
import kotlinx.android.synthetic.internal.debug_view.view.*

class DebugView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  @SuppressLint("CheckResult")
  override fun onAttachedToWindow() {
    super.onAttachedToWindow()

    ((context.applicationContext as BlinkistChallengeApplication)
        .component as DebugAppComponent).injectDebugView(this)

    feature_switch.checkedChanges().subscribe {
      val isFeatureOn = if (it) GroupByWeeklyFeature.On else GroupByWeeklyFeature.Off
      FeatureRepository.weeklyFeature.onNext(isFeatureOn)
    }
  }

}