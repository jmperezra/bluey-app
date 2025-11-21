package com.jmperezra.bluey.core.di

import org.koin.dsl.module

val coreModule = module {
    includes(remoteModule)
    includes(providerModule)
}