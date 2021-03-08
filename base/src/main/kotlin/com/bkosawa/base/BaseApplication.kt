package com.bkosawa.base

import android.app.Application
import com.bkosawa.base.di.AppInjector
import com.bkosawa.base.di.ApplicationComponent
import com.bkosawa.base.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = createDiComponent().apply { inject(this@BaseApplication) }
        AppInjector.init(this)
    }

    private fun createDiComponent(): ApplicationComponent =
        DaggerApplicationComponent.builder()
            .application(this)
            .build()

    override fun androidInjector(): AndroidInjector<Any> = AppInjector.decorateAndroidInjector(androidInjector, component)
}