package com.example.countries.data.network.response

import kotlinx.coroutines.flow.*

inline fun <DB, REMOTE> networkBoundResource(
    crossinline fetchFromRemote: suspend () -> Flow<ApiResponse<REMOTE>>,
    crossinline fetchFromLocal: suspend () -> Flow<DB>,
    crossinline shouldFetchFromRemote: suspend (DB?) -> Boolean = { true },
    crossinline processRemoteData: suspend (response: ApiSuccessResponse<REMOTE>) -> DB,
    crossinline saveRemoteData: suspend (DB) -> Unit = { }
) = flow<Resource<DB>> {

    emit(Resource.loading())

    val localData = fetchFromLocal().first()

    if (shouldFetchFromRemote(localData)) {
        emit(Resource.loading(localData))
        fetchFromRemote().collect { apiResponse ->
            when (apiResponse) {
                is ApiEmptyResponse -> {
                    // emit success with null data
                    emit(Resource.success(null))
                }
                is ApiErrorResponse -> {
                    // onFetch Failed
                    // emit error with DB data
                    emitAll(fetchFromLocal().map { dbData ->
                        Resource.error(
                            apiResponse.errorMessage,
                            dbData
                        )
                    })
                }
                is ApiSuccessResponse -> {
                    // process apiResponse<Remote> to DB
                    // save DB
                    // emitAll DB data
                    val processedData = processRemoteData(apiResponse)
                    saveRemoteData(processedData)
                    emitAll(fetchFromLocal().map { dbData ->
                        Resource.success(dbData)
                    })
                }
            }
        }
    } else {
        // emitAll DB data
        emitAll(fetchFromLocal().map { dbData ->
            Resource.success(dbData)
        })
    }
}