package com.blinkslabs.blinkist.android.challenge.presentation.screen.books.rootview

import android.app.Activity
import android.view.ViewGroup

/** Provides a view container for the main Activity's view. */
interface ViewContainer {

  /** The root [android.view.ViewGroup] into which the activity should place its contents.  */
  fun forActivity(activity: Activity): ViewGroup

  /** An [ViewContainer] which returns the normal activity content view.  */
  object Default : ViewContainer {

    override fun forActivity(activity: Activity): ViewGroup =
        activity.findViewById(android.R.id.content) as ViewGroup
  }

}