package com.example.countries.domain.repository

import com.example.countries.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    fun search(string: String): Flow<List<Country>>
}