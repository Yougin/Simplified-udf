package com.labs.biletskiy.android.app

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.drawerlayout.widget.DrawerLayout
import com.labs.biletskiy.android.challenge.R
import com.labs.biletskiy.android.presentation.screen.books.rootview.ViewContainer


class DebugViewContainer : ViewContainer {

  override fun forActivity(activity: Activity): ViewGroup {
    activity.setContentView(R.layout.debug_activity_frame)
    val drawer = activity.findViewById(R.id.debug_drawer) as DrawerLayout
    drawer.addView(getView(activity, drawer), getLayoutParams())

    return activity.findViewById(R.id.content) as ViewGroup
  }

  private fun getView(activity: Activity, drawer: DrawerLayout): View =
      LayoutInflater.from(activity).inflate(R.layout.debug_view, drawer, false)

  private fun getLayoutParams(): DrawerLayout.LayoutParams =
      DrawerLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT).apply { gravity = Gravity.END }
}