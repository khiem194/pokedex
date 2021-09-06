package com.kdnt.pokedex.di

import com.google.gson.Gson
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.kdnt.pokedex.BuildConfig
import com.kdnt.pokedex.data.api.ApiPokemonService
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { getRetrofit().create(ApiPokemonService::class.java) }
    single { provideOkHttpClient() }
}

const val BASE_URL = "https://pokeapi.co/api/v2/"

fun getRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(provideOkHttpClient())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("lang", Locale.getDefault().language)
                    .build()
            chain.proceed(request)
        }
        .addInterceptor(
            LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BODY)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build()
        )
    return builder.build()
}