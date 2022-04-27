package com.example.countries.api

import com.example.countries.data.network.dto.CountryNt
import com.example.countries.data.network.flowAdapter.NetworkResponseAdapterFactory
import com.example.countries.data.network.response.ApiResponse
import com.example.countries.utilities.BASE_URL
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountriesService {

    @GET("all")
    fun getAllCountries(): Flow<ApiResponse<List<CountryNt>>>

    companion object {
        fun create(): CountriesService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(NetworkResponseAdapterFactory.create())
                .build()
                .create(CountriesService::class.java)
        }
    }
}