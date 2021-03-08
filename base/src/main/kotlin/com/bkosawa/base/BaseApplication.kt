package com.bkosawa.base

import android.app.Application
import com.bkosawa.base.di.ApplicationComponent
import com.bkosawa.base.di.DaggerApplicationComponent

class BaseApplication: Application() {
    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = createDiComponent()
    }

    private fun createDiComponent(): ApplicationComponent =
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
}