package com.bkosawa.gradle

import com.bkosawa.gradle.extension.applyAndroidApplicationPlugins
import com.bkosawa.gradle.extension.applyAndroidBasicConfiguration
import com.bkosawa.gradle.extension.applyKotlinConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project

class ApplicationModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            applyAndroidApplicationPlugins()
            applyAndroidBasicConfiguration {
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = true
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro", "proguard-rules-release.pro")
                    }
                }
            }
            applyKotlinConfiguration()
        }
    }

}