package com.jmperezra.bluey.core.di

import com.jmperezra.bluey.feature.characters.di.characterModule
import org.koin.dsl.module

val featureModule = module {
    includes(characterModule)
}