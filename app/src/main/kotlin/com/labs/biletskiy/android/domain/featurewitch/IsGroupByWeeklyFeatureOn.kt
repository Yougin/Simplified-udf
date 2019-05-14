package com.labs.biletskiy.android.domain.featurewitch

import com.labs.biletskiy.android.data.featureswitch.FeatureRepository
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
