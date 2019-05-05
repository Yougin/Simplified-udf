package com.blinkslabs.blinkist.android.challenge.domain.featurewitch

import com.blinkslabs.blinkist.android.challenge.data.featureswitch.FeatureRepository
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

interface IsGroupByWeeklyFeatureOn {

  operator fun invoke(): Observable<GroupByWeeklyFeature>
}

class IsGroupByWeeklyFeatureOnUseCase @Inject constructor() : IsGroupByWeeklyFeatureOn {

  override fun invoke(): Observable<GroupByWeeklyFeature> =
      FeatureRepository.weeklyFeature
          .startWith(GroupByWeeklyFeature.On)
          .doOnNext { Timber.d("----- Emits $it") }
}

sealed class GroupByWeeklyFeature {
  object On : GroupByWeeklyFeature()
  object Off : GroupByWeeklyFeature()
}
