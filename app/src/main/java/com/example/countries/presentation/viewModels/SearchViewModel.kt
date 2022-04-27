package com.example.countries.viewModels

import androidx.lifecycle.*
import com.example.countries.data.database.dto.CountryDb
import com.example.countries.data.repository.SearchRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepositoryImpl: SearchRepositoryImpl
) : ViewModel() {

    fun search (string: String): Flow<List<CountryDb>> = flow {
        searchRepositoryImpl.search(string).collect{
            emit(it)
        }
    }

}