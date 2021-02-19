package com.bkosawa.base.di

import android.app.Application
import com.bkosawa.base.BaseApplication
import com.bkosawa.base.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}