package com.blinkslabs.blinkist.android.challenge.util

import android.content.Context
import androidx.annotation.StringRes
import android.widget.Toast

fun Context.showToast(@StringRes textRes: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, textRes, duration).show()
}
