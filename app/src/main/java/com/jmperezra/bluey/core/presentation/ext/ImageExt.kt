package com.jmperezra.bluey.core.presentation.ext

import android.widget.ImageView
import coil3.load

fun ImageView.fromUrl(url: String) {
    this.load(url)
}