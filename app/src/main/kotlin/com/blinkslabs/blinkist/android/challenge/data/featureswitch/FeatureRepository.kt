package com.blinkslabs.blinkist.android.challenge.data.featureswitch

import com.blinkslabs.blinkist.android.challenge.domain.featurewitch.GroupByWeeklyFeature
import io.reactivex.subjects.PublishSubject

// This one is made so just to gain time, ideally it should be implemented
// the same way BookRepository is done - exposing an observable data set.
object FeatureRepository {

  val weeklyFeature: PublishSubject<GroupByWeeklyFeature> = PublishSubject.create()
}



