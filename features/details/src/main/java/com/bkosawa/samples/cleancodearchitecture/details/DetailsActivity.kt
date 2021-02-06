package com.bkosawa.samples.cleancodearchitecture.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bkosawa.samples.detail.databinding.ActivityScrollingBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbar.apply {
            title = "Chocolate Cake"
            setSupportActionBar(this)
        }

//        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }
}