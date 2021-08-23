package com.kdnt.pokedex

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.kdnt.pokedex.core.logger.initLogger
import com.kdnt.pokedex.di.networkModule
import com.kdnt.pokedex.di.persistenceModule
import com.kdnt.pokedex.di.repositoryModule
import com.kdnt.pokedex.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {

        }
        this.initLogger(BuildConfig.DEBUG)
        startKoin {
            androidContext(this@PokedexApplication)
            androidLogger(
                if (BuildConfig.DEBUG) Level.DEBUG else Level.ERROR
            )
            modules(networkModule)
            modules(persistenceModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
        Kotpref.init(this)
    }
}