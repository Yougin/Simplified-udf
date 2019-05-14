package com.blinkslabs.blinkist.android.challenge.app

import com.blinkslabs.blinkist.android.challenge.data.DataModule
import com.blinkslabs.blinkist.android.challenge.util.UtilsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DebugUiModule::class, UtilsModule::class])
interface DebugAppComponent : AppComponent {

  fun injectDebugView(view: DebugView)
}