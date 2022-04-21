package com.example.countries.viewModels

import androidx.lifecycle.*
import com.example.countries.data.model.detabaseModel.CountryDb
import com.example.countries.repositories.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    fun search (string: String): Flow<List<CountryDb>> = flow {
        searchRepository.search(string).collect{
            emit(it)
        }
    }

}