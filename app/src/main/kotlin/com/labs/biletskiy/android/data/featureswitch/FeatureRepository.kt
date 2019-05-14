package com.labs.biletskiy.android.data.featureswitch

import com.labs.biletskiy.android.domain.featurewitch.GroupByWeeklyFeature
import io.reactivex.subjects.PublishSubject

// This one is made so just to save time, ideally it should be implemented
// the same way BookRepository is done - by exposing an API which represent an observable data set.
object FeatureRepository {

  val weeklyFeature: PublishSubject<GroupByWeeklyFeature> = PublishSubject.create()
}



