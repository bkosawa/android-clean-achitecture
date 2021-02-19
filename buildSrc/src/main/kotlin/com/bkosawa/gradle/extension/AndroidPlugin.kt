package com.bkosawa.gradle.extension

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.applyAndroidBasicConfiguration(otherConfigurations: BaseExtension.() -> Unit = {}) {
    android.run {
        androidBasicConfiguration(this@applyAndroidBasicConfiguration, otherConfigurations)
    }
}

private val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Not an Android module $name")

private fun BaseExtension.androidBasicConfiguration(project: Project,otherConfigurations: BaseExtension.() -> Unit = {}) {
    compileSdkVersion(BuildVersion.compileSdkVersion)

    defaultConfig {
        minSdkVersion(BuildVersion.minSdkVersion)
        targetSdkVersion(BuildVersion.targetSdkVersion)
        versionCode = AppVersion.versionCode
        versionName = AppVersion.versionName

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildFeatures.viewBinding = true

    lintOptions {
        // set to true to turn off analysis progress reporting by lint
        isQuiet = false
        // if true, stop the gradle build if errors are found
        isAbortOnError = true
        // if true, only report errors
        isIgnoreWarnings = false
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }

    testOptions.unitTests.isIncludeAndroidResources = true

    packagingOptions {
        exclude("META-INF/app_debug.kotlin_module")
        exclude("META-INF/proguard/androidx-annotations.pro")
    }

    buildTypes {
        getByName("debug") {
            isTestCoverageEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    otherConfigurations(this)
}