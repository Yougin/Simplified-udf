package com.blinkslabs.blinkist.android.challenge.domain.featureswitch

import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

interface IsGroupByWeeklyFeatureOn {

  operator fun invoke(): Observable<Boolean>
}

class IsGroupByWeeklyFeatureOnUseCase @Inject constructor() : IsGroupByWeeklyFeatureOn {

  override fun invoke(): Observable<Boolean> =
      Observable.just(true).doOnNext { Timber.d("----- Emits $it") }
}