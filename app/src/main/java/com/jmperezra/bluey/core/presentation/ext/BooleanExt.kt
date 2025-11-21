package com.jmperezra.bluey.core.presentation.ext

fun Boolean.fold(isTrue: () -> Unit, isFalse: () -> Unit) {
    if (this) {
        isTrue()
    } else {
        isFalse()
    }
}