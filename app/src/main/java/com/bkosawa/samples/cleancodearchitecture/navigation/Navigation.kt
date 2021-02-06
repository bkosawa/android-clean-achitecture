package com.bkosawa.samples.cleancodearchitecture.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.bkosawa.samples.cleancodearchitecture.R
import com.bkosawa.samples.cleancodearchitecture.resource.ResourceProvider
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

private const val packageName = "com.bkosawa.samples.cleancodearchitecture"
private const val homeSampleClassname = "$packageName.home.HomeActivity"
private const val detailsSampleClassname = "$packageName.details.DetailsActivity"

class Navigation(
    private val activity: AppCompatActivity,
    private val resourceProvider: ResourceProvider
) : LifecycleObserver {
    init {
        activity.lifecycle.addObserver(this)
    }

    /** Listener used to handle changes in state for install requests. */
    private val listener = SplitInstallStateUpdatedListener { state ->
        val multiInstall = state.moduleNames().size > 1
        val names = state.moduleNames().joinToString(" - ")
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                //  In order to see this, the application has to be uploaded to the Play Store.
//                displayLoadingState(state, "Downloading $names")
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                /*
                  This may occur when attempting to download a sufficiently large module.

                  In order to see this, the application has to be uploaded to the Play Store.
                  Then features can be requested until the confirmation path is triggered.
                 */
//                startIntentSender(state.resolutionIntent()?.intentSender, null, 0, 0, 0)
            }
            SplitInstallSessionStatus.INSTALLED -> {
                onSuccessfulLoad(names, launch = !multiInstall, keepCurrentActivity = true)
            }

            SplitInstallSessionStatus.INSTALLING -> {
                // displayLoadingState(state, "Installing $names")
            }
            SplitInstallSessionStatus.FAILED -> {
//                toastAndLog("Error: ${state.errorCode()} for module ${state.moduleNames()}")
            }
        }
    }

    val moduleHome by lazy { resourceProvider.getString(R.string.module_feature_home) }
    val moduleDetails by lazy { resourceProvider.getString(R.string.module_feature_details) }

    private val manager: SplitInstallManager = SplitInstallManagerFactory.create(activity)

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener() {
        manager.registerListener(listener)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnectListener() {
        manager.unregisterListener(listener)
    }


    /**
     * Load a feature by module name.
     * @param name The name of the feature module to load.
     */
    fun loadAndLaunchModule(name: String, keepCurrentActivity: Boolean = false) {
//        updateProgressMessage("Loading module $name")
        // Skip loading if the module already is installed. Perform success action directly.
        if (manager.installedModules.contains(name)) {
//            updateProgressMessage("Already installed")
            onSuccessfulLoad(name, launch = true, keepCurrentActivity)
            return
        }

        // Create request to install a feature module by name.
        val request = SplitInstallRequest.newBuilder()
            .addModule(name)
            .build()

        // Load and install the requested feature module.
        manager.startInstall(request)

//        updateProgressMessage("Starting install for $name")
    }

    /**
     * Define what to do once a feature module is loaded successfully.
     * @param moduleName The name of the successfully loaded module.
     * @param launch `true` if the feature module should be launched, else `false`.
     */
    private fun onSuccessfulLoad(moduleName: String, launch: Boolean, keepCurrentActivity: Boolean) {
        if (launch) {
            when (moduleName) {
                moduleHome -> launchActivity(homeSampleClassname, keepCurrentActivity)
                moduleDetails -> launchActivity(detailsSampleClassname, keepCurrentActivity)
            }
        }
    }

    /** Launch an activity by its class name. */
    private fun launchActivity(className: String, keepCurrentActivity: Boolean) {
        Intent().setClassName(packageName, className)
            .also {
                activity.startActivity(it)
            }
        if (!keepCurrentActivity) {
            activity.finish()
        }
    }
}