package com.example.countries.viewModels

import androidx.lifecycle.*
import com.example.countries.data.database.dto.CountryDb
import com.example.countries.data.network.response.Resource
import com.example.countries.data.repository.CountriesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesRepositoryImpl: CountriesRepositoryImpl,
) : ViewModel() {

    val selectedCountry = MutableLiveData<CountryDb>()

    val countries: LiveData<Resource<List<CountryDb>>> = countriesRepositoryImpl.getAllCountries().map {
        when (it.status) {
            Resource.Status.LOADING -> {
                Resource.loading(null)
            }
            Resource.Status.SUCCESS -> {
                Resource.success(it.data)
            }
            Resource.Status.ERROR -> {
                Resource.error(it.message!!, null)
            }
        }
    }.asLiveData(viewModelScope.coroutineContext)

    fun selectCountry(country: CountryDb) = selectedCountry.postValue(country)

}