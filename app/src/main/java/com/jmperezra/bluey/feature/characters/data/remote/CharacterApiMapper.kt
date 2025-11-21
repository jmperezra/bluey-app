package com.jmperezra.bluey.feature.characters.data.remote

import com.jmperezra.bluey.feature.characters.domain.Character

fun CharacterApiModel.toDomain(): Character {
    return Character(
        this.id,
        this.name,
        "",
        "",
        this.shortDescription,
        "",
        "",
        "",
        this.urlPhoto
    )
}