package com.blinkslabs.blinkist.android.challenge.app

import com.blinkslabs.blinkist.android.challenge.data.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DebugUiModule::class])
interface DebugAppComponent : AppComponent {

  fun injectDebugView(view: DebugView)
}