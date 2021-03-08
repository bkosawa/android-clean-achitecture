package com.bkosawa.base.di

import android.app.Application
import com.bkosawa.base.BaseApplication
import dagger.BindsInstance
import dagger.Component

@Component
interface ApplicationComponent {
    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}