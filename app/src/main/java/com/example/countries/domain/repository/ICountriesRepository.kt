package com.example.countries.domain.repository

import com.example.countries.data.network.response.Resource
import com.example.countries.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface ICountriesRepository {
    fun getAllCountries(): Flow<Resource<List<Country>>>
}