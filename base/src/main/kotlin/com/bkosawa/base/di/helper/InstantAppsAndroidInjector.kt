package com.bkosawa.base.di.helper

import android.app.Activity
import androidx.fragment.app.Fragment
import com.bkosawa.base.di.ApplicationComponent
import dagger.android.AndroidInjector

@Suppress("UNCHECKED_CAST")
class InstantAppsAndroidInjector(
    private val applicationComponent: ApplicationComponent,
    private val activityMap: MutableMap<Class<out Activity>, (ApplicationComponent) -> AndroidInjector.Builder<out Activity>>,
    private val fragmentMap: MutableMap<Class<out Fragment>, (ApplicationComponent) -> AndroidInjector.Builder<out Fragment>>,
    private val decoratedAndroidInjector: AndroidInjector<Any>
) : AndroidInjector<Any> {

    override fun inject(instance: Any) {
        when {
            activityMap.contains(instance.activityClass()) -> inject(instance as Activity, instance.activityClass())
            fragmentMap.contains(instance.fragmentClass()) -> inject(instance as Fragment, instance.fragmentClass())
            else -> decoratedAndroidInjector.inject(instance)
        }
    }

    private inline fun <reified T : Activity> inject(instance: Activity, clazz: Class<out T>) {
        (activityMap[clazz]?.invoke(applicationComponent) as AndroidInjector.Builder<Activity>)
            .create(instance.cast())?.inject(instance.cast())
    }

    private fun Any.activityClass(): Class<out Activity> = this.javaClass as Class<out Activity>

    private inline fun <reified T : Activity> Activity.cast(): T = this as T

    private inline fun <reified T : Fragment> inject(instance: Fragment, clazz: Class<out T>) {
        (fragmentMap[clazz]?.invoke(applicationComponent) as AndroidInjector.Builder<Fragment>)
            .create(instance.cast())?.inject(instance.cast())
    }

    private fun Any.fragmentClass(): Class<out Fragment> = this.javaClass as Class<out Fragment>

    private inline fun <reified T : Fragment> Fragment.cast(): T = this as T

}
