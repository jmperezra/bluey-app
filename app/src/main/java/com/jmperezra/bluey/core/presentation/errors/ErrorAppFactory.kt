package com.jmperezra.bluey.core.presentation.errors

import android.content.Context
import com.jmperezra.bluey.core.domain.ErrorApp

class ErrorAppFactory(val context: Context) {

    fun build(errorApp: ErrorApp, onClick: (() -> Unit)): ErrorAppUI {
        return when (errorApp) {
            ErrorApp.InternetError -> ConnectionErrorAppUI(context, onClick)
            ErrorApp.ServerError -> ServerErrorAppUI(context, onClick)
            else -> {
                UnknownErrorAppUI(context, onClick)
            }
        }
    }
}