package com.bkosawa.base.navigator

import android.app.Activity
import android.content.Intent
import android.net.Uri
import javax.inject.Inject

class AppNavigator @Inject constructor() {

    private fun Uri.startDeepLink(activity: Activity) {
        Intent(Intent.ACTION_VIEW, this).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            `package` = activity.packageName
            activity.startActivity(this)
        }
    }
}