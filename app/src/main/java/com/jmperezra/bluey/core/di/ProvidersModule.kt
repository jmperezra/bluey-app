package com.jmperezra.bluey.core.di

import com.jmperezra.bluey.core.presentation.errors.ErrorAppFactory
import com.jmperezra.bluey.core.providers.TimeDataProvider
import com.jmperezra.bluey.core.providers.TimeProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val providerModule = module {
    singleOf(::TimeDataProvider) bind TimeProvider::class
    singleOf(::ErrorAppFactory)
}