package com.blinkslabs.blinkist.android.challenge.domain.featurewitch

import io.reactivex.Observable
import io.reactivex.Observable.just
import timber.log.Timber
import javax.inject.Inject

sealed class GroupByWeeklyFeature {

  object On : GroupByWeeklyFeature()
  object Off : GroupByWeeklyFeature()
}

interface IsGroupByWeeklyFeatureOn {

  operator fun invoke(): Observable<GroupByWeeklyFeature>
}

class IsGroupByWeeklyFeatureOnUseCase @Inject constructor() : IsGroupByWeeklyFeatureOn {

  override fun invoke(): Observable<GroupByWeeklyFeature> =
      just<GroupByWeeklyFeature>(GroupByWeeklyFeature.Off).doOnNext { Timber.d("----- Emits $it") }
}