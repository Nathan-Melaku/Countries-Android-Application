package com.example.countries.repositories

import com.example.countries.data.network.api.CountriesService
import com.example.countries.data.database.CountryDao
import com.example.countries.data.database.dto.CountryDb
import com.example.countries.data.network.response.Resource
import com.example.countries.data.network.response.networkBoundResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val countriesService: CountriesService,
    private val countriesDao: CountryDao,
    private val dispatcher: CoroutineDispatcher
) {

    fun getAllCountries(): Flow<Resource<List<CountryDb>>> {
        return networkBoundResource(
            fetchFromRemote = {
                Timber.d("Fetching From Remote")
                val remoteResult = countriesService.getAllCountries()
                remoteResult
            },
            fetchFromLocal = {
                Timber.d("Fetching From Local")
                val localResult = countriesDao.getCountries()
                localResult
            },
            shouldFetchFromRemote = {
                Timber.d("$it")
                it.isEmpty()
            },
            processRemoteData = { response ->
                val listOfCountryDb = mutableListOf<CountryDb>()
                for (country in response.body!!) {
                    val countryDb: CountryDb = CountryDb.fromCountryNt(country)
                    listOfCountryDb.add(countryDb)
                }
                Timber.d("Countries inserted Tod Database: $listOfCountryDb")
                listOfCountryDb.toList()
            },
            saveRemoteData = { listCountryDb ->
                countriesDao.insertAll(*listCountryDb.toTypedArray())
                Timber.d("Database insertion Complete")
            }
        ).flowOn(dispatcher)
    }
}