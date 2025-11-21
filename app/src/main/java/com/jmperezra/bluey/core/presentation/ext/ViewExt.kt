package com.jmperezra.bluey.core.presentation.ext

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

