package com.jmperezra.bluey.core.presentation.errors

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.jmperezra.bluey.core.presentation.ext.hide
import com.jmperezra.bluey.core.presentation.ext.visible
import com.jmperezra.bluey.databinding.ViewErrorBinding

class ErrorAppView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {


    private val binding = ViewErrorBinding
        .inflate(LayoutInflater.from(context), this, true)

    init {
        hide()
    }

    fun render(errorAppUI: ErrorAppUI) {
        binding.apply {
            imageError.setImageResource(errorAppUI.getImageError())
            titleError.text = errorAppUI.getTitleError()
            descriptionError.text = errorAppUI.getDescriptionError()
            buttonRetryError.setOnClickListener {
                errorAppUI.getActionRetry()
            }
            visible()
        }
    }
}