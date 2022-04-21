package com.example.countries.data.network.flowAdapter

import com.example.countries.data.network.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.awaitResponse
import java.lang.reflect.Type

class NetworkResponseAdapter(
    private val successType: Type
) : CallAdapter<Type, Flow<ApiResponse<Type>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<Type>): Flow<ApiResponse<Type>> = flow {
        val response = call.awaitResponse()
        emit(ApiResponse.create(response))
    }.catch { error ->
        emit(ApiResponse.create(error))
    }
}