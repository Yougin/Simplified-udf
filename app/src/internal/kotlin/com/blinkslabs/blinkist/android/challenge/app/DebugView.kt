package com.blinkslabs.blinkist.android.challenge.app

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

class DebugView(context: Context, attrs: AttributeSet?) : ScrollView(context, attrs) {

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()

//    ((context.applicationContext as App).component as DebugAppComponent).injectDebugView(this)

//    isMockMode.asObservable().subscribe {
//      RxCompoundButton.checked(mockModeSwitch).accept(it)
//      RxCompoundButton.checkedChanges(mockModeSwitch).skip(1).subscribe {
//        isMockMode.set(it)
//        ProcessPhoenix.triggerRebirth(context)
//      }
//    }

  }

}