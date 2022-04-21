package com.example.countries.di

import com.example.countries.api.CountriesService
import com.example.countries.repositories.CountriesRepository
import com.example.countries.data.database.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideCountriesService(): CountriesService = CountriesService.create()

    @Singleton
    @Provides
    fun providesCountriesRepository(
        countriesService: CountriesService,
        countriesDao: CountryDao,
        dispatcher: CoroutineDispatcher
    ): CountriesRepository = CountriesRepository(countriesService,countriesDao,dispatcher)
}