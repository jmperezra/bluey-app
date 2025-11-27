package com.jmperezra.bluey.core.data.local.xml.policies

import com.jmperezra.bluey.core.data.local.xml.XmlModel
import com.jmperezra.bluey.core.providers.TimeProvider
import java.util.concurrent.TimeUnit

class TtlCachePolicy<T : XmlModel>(
    val ttl: Long,
    val timeUnit: TimeUnit,
    val timeProvider: TimeProvider
) : CachePolicy<T> {

    private val ttlMillis: Long = timeUnit.toMillis(ttl)

    override fun isValid(data: T?): Boolean {
        return data != null && data.getPersistedTime() + ttlMillis > timeProvider.getCurrentTimeInMs()
    }
}