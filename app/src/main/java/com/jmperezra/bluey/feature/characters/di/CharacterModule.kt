package com.jmperezra.bluey.feature.characters.di

import com.jmperezra.bluey.core.data.local.xml.XmlCacheStorage
import com.jmperezra.bluey.core.data.local.xml.policies.CachePolicy
import com.jmperezra.bluey.core.data.local.xml.policies.TtlCachePolicy
import com.jmperezra.bluey.feature.characters.data.CharacterDataRepository
import com.jmperezra.bluey.feature.characters.data.local.xml.CharacterXmlModel
import com.jmperezra.bluey.feature.characters.data.local.xml.CharactersXmlLocalDataSource
import com.jmperezra.bluey.feature.characters.data.remote.CharacterApiService
import com.jmperezra.bluey.feature.characters.data.remote.CharactersApiRemoteDataSource
import com.jmperezra.bluey.feature.characters.domain.CharacterRepository
import com.jmperezra.bluey.feature.characters.domain.GetCharacterDetailUseCase
import com.jmperezra.bluey.feature.characters.domain.GetCharactersUseCase
import com.jmperezra.bluey.feature.characters.presentation.CharacterDetailViewModel
import com.jmperezra.bluey.feature.characters.presentation.CharactersViewModel
import com.jmperezra.bluey.feature.characters.presentation.adapter.CharactersAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.serializer
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val characterModule = module {

    viewModelOf(::CharactersViewModel)
    factoryOf(::CharacterDataRepository) bind CharacterRepository::class
    factoryOf(::CharactersAdapter)

    //Character Detail
    factoryOf(::GetCharacterDetailUseCase)
    factoryOf(::CharacterDetailViewModel)

    factory {
        GetCharactersUseCase(get())
    }

    factory {
        XmlCacheStorage(get(), "characters_cache", serializer<CharacterXmlModel>())
    }

    factory {
        //Bind
        TtlCachePolicy<CharacterXmlModel>(
            60,
            TimeUnit.MINUTES,
            get()
        ) as CachePolicy<CharacterXmlModel>
    }
    factory {
        CharactersXmlLocalDataSource(get(), get(), get())
    }

    factory {
        get<Retrofit>().create(CharacterApiService::class.java)
    }

    factory {
        Dispatchers.IO
    }

    factory {
        CharactersApiRemoteDataSource(get(), get())
    }


}