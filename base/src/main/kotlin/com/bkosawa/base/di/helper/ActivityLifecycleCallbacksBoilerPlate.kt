package com.bkosawa.base.di.helper

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.bkosawa.base.extension.DO_NOTHING

class ActivityLifecycleCallbacksBoilerPlate : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(p0: Activity?, p1: Bundle?) = DO_NOTHING
    override fun onActivityStarted(p0: Activity?) = DO_NOTHING
    override fun onActivityResumed(p0: Activity?) = DO_NOTHING
    override fun onActivityPaused(p0: Activity?) = DO_NOTHING
    override fun onActivityStopped(p0: Activity?) = DO_NOTHING
    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) = DO_NOTHING
    override fun onActivityDestroyed(p0: Activity?) = DO_NOTHING
}