package com.blinkslabs.blinkist.android.challenge.util

import io.reactivex.Observable

fun <T, U : T> Observable<T>.takeOnlyOnce(clazz: Class<U>)
    : Observable<T> =
    compose { upstream ->
      upstream.publish { shared ->
        Observable.merge(
            shared.ofType(clazz).take(1),
            shared.filter { !clazz.isInstance(it) }
        )
      }
    }
