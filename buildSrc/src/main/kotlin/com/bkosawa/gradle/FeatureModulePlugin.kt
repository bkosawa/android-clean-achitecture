package com.bkosawa.gradle

import com.bkosawa.gradle.extension.applyAndroidBasicConfiguration
import com.bkosawa.gradle.extension.applyAndroidDynamicFeaturePlugins
import com.bkosawa.gradle.extension.applyKotlinConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project

class FeatureModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            applyAndroidDynamicFeaturePlugins()
            applyAndroidBasicConfiguration {
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }
            }
            applyKotlinConfiguration()
        }
    }

}