package com.bkosawa.samples.cleancodearchitecture.home

import android.os.Bundle
import android.widget.TextView
import com.bkosawa.samples.cleancodearchitecture.BaseSplitActivity
import com.bkosawa.samples.cleancodearchitecture.home.usecases.ShowHomeUseCase

class HomeActivity : BaseSplitActivity() {

    val useCase = ShowHomeUseCase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_home)
        findViewById<TextView>(R.id.homeTitle).text =
            getString(R.string.title_home, useCase().value)
    }

}
