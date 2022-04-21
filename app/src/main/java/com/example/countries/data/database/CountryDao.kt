package com.example.countries.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.countries.data.model.detabaseModel.CountryDb
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM CountryDb")
    fun getCountries(): Flow<List<CountryDb>>

    @Insert
    suspend fun insertAll(vararg countries: CountryDb)

    @Update
    suspend fun updateAll(vararg countries: CountryDb)

    @Query("SELECT * FROM CountryDb WHERE commonName LIKE '%' || :search || '%' ")
    fun searchByString(search: String): Flow<List<CountryDb>>
}