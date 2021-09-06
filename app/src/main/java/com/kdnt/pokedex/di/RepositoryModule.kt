package com.kdnt.pokedex.di

import com.kdnt.pokedex.data.repository.PokedexRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PokedexRepository(get()) }
}
