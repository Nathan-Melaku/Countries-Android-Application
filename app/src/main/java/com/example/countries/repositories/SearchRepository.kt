package com.example.countries.repositories

import com.example.countries.data.database.CountryDao
import com.example.countries.data.model.detabaseModel.CountryDb
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val countryDao: CountryDao,
    private val dispatcher: CoroutineDispatcher
) {

    fun search(string: String): Flow<List<CountryDb>> = flow {
        Timber.d("fetching from Database")
        countryDao.searchByString(string).collect {
            emit(it)
        }
    }.flowOn(dispatcher)
}