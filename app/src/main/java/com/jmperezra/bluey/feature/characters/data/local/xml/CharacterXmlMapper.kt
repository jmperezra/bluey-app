package com.jmperezra.bluey.feature.characters.data.local.xml

import com.jmperezra.bluey.feature.characters.domain.Character

fun Character.toXmlModel(createdAt: Long): CharacterXmlModel {
    return CharacterXmlModel(this, createdAt)
}

fun CharacterXmlModel.toModel(): Character {
    return this.character
}