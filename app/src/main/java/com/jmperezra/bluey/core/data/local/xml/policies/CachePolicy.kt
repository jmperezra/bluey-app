package com.jmperezra.bluey.core.data.local.xml.policies

import com.jmperezra.bluey.core.data.local.xml.XmlModel

interface CachePolicy<T : XmlModel> {
    fun isValid(data: T): Boolean
}