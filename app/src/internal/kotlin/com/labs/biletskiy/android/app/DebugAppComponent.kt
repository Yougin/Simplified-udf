package com.labs.biletskiy.android.app

import com.labs.biletskiy.android.data.DataModule
import com.labs.biletskiy.android.util.UtilsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DebugUiModule::class, UtilsModule::class])
interface DebugAppComponent : AppComponent {

  fun injectDebugView(view: DebugView)
}