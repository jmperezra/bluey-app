package com.jmperezra.bluey.core.providers

interface TimeProvider {
    fun getCurrentTimeInMs(): Long
}

class TimeDataProvider() : TimeProvider {
    override fun getCurrentTimeInMs(): Long = System.currentTimeMillis()
}