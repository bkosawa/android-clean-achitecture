package com.bkosawa.samples.cleancodearchitecture.resource

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(pic: String) {
    Glide.with(this.context)
        .load(pic)
        .into(this)
}