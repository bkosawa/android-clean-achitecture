package com.bkosawa.gradle.extension

import org.gradle.api.Project
import org.gradle.api.plugins.ObjectConfigurationAction

internal fun Project.applyAndroidApplicationPlugins() {
    apply {
        androidApplicationPlugin()
        kotlinAndroidPlugin()
        kotlinKaptPlugin()
    }
}

private fun ObjectConfigurationAction.androidApplicationPlugin() = plugin("com.android.application")

private fun ObjectConfigurationAction.kotlinAndroidPlugin() = plugin("kotlin-android")

private fun ObjectConfigurationAction.kotlinKaptPlugin() = plugin("kotlin-kapt")