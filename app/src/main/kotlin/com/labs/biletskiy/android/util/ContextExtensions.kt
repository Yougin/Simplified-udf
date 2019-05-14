package com.labs.biletskiy.android.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes textRes: Int, duration: Int = Toast.LENGTH_LONG) {
  Toast.makeText(this, textRes, duration).show()
}
