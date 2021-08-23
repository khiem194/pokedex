package com.kdnt.pokedex.di

import com.kdnt.pokedex.ui.details.DetailsViewModel
import com.kdnt.pokedex.ui.home.HomeViewModel
import com.kdnt.todoapp.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { SplashViewModel() }
    viewModel { HomeViewModel() }
    viewModel { DetailsViewModel() }

}
