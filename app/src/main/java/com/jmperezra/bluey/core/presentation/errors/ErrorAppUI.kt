package com.jmperezra.bluey.core.presentation.errors

import android.content.Context
import com.jmperezra.bluey.R

interface ErrorAppUI {
    fun getImageError(): Int
    fun getTitleError(): String
    fun getDescriptionError(): String
    fun getActionRetry(): Unit
}

class ConnectionErrorAppUI(val context: Context, val onClick: (() -> Unit)?) : ErrorAppUI {
    override fun getImageError(): Int {
        return R.drawable.img_network_error
    }

    override fun getTitleError(): String {
        return context.getString(R.string.title_error_connection)
    }

    override fun getDescriptionError(): String {
        return context.getString(R.string.description_error_connection)
    }

    override fun getActionRetry() {
        onClick?.invoke()
    }
}

class ServerErrorAppUI(val context: Context, val onClick: (() -> Unit)?) : ErrorAppUI {
    override fun getImageError(): Int {
        return R.drawable.img_unknow_error
    }

    override fun getTitleError(): String {
        return context.getString(R.string.title_error_server)
    }

    override fun getDescriptionError(): String {
        return context.getString(R.string.description_error_server)
    }

    override fun getActionRetry() {
        onClick?.invoke()
    }
}

class UnknownErrorAppUI(val context: Context, val onClick: (() -> Unit)?) : ErrorAppUI {
    override fun getImageError(): Int {
        return R.drawable.img_unknow_error
    }

    override fun getTitleError(): String {
        return context.getString(R.string.title_error_unknown)
    }

    override fun getDescriptionError(): String {
        return context.getString(R.string.description_error_unknown)
    }

    override fun getActionRetry() {
        onClick?.invoke()
    }
}