package com.bkosawa.samples.cleancodearchitecture.home

import android.os.Bundle
import com.bkosawa.samples.cleancodearchitecture.BaseSplitActivity

class HomeActivity : BaseSplitActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_home)
    }

}
