package com.bkosawa.samples.cleancodearchitecture

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import com.bkosawa.samples.cleancodearchitecture.navigation.Navigation
import com.bkosawa.samples.cleancodearchitecture.resource.ResourceProvider
import com.google.android.play.core.splitinstall.SplitInstallSessionState

class MainActivity : AppCompatActivity() {

    private lateinit var progress: Group
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView

    private lateinit var navigation: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = Navigation(this, ResourceProvider(this.applicationContext))

        initializeViews()
    }

    override fun onResume() {
        navigation.loadAndLaunchModule(navigation.moduleHome)
        super.onResume()
    }

    /** Display a loading state to the user. */
    private fun displayLoadingState(state: SplitInstallSessionState, message: String) {
        displayProgress()

        progressBar.max = state.totalBytesToDownload().toInt()
        progressBar.progress = state.bytesDownloaded().toInt()

        updateProgressMessage(message)
    }

    private fun initializeViews() {
        progress = findViewById(R.id.progress)
        progressBar = findViewById(R.id.progress_bar)
        progressText = findViewById(R.id.progress_text)
    }

    private fun updateProgressMessage(message: String) {
        if (progress.visibility != View.VISIBLE) displayProgress()
        progressText.text = message
    }

    /** Display progress bar and text. */
    private fun displayProgress() {
        progress.visibility = View.VISIBLE
    }
}

fun MainActivity.toastAndLog(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    Log.d(TAG, text)
}

private const val TAG = "DynamicFeatures"