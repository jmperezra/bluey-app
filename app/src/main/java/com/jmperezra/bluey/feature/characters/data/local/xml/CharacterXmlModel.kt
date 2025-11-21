package com.jmperezra.bluey.feature.characters.data.local.xml

import com.jmperezra.bluey.core.data.local.xml.XmlModel
import com.jmperezra.bluey.feature.characters.domain.Character
import kotlinx.serialization.Serializable

@Serializable
class CharacterXmlModel(val character: Character, val createdAt: Long) : XmlModel {

    override fun getId(): String {
        return character.id
    }

    override fun getPersistedTime(): Long {
        return createdAt
    }
}