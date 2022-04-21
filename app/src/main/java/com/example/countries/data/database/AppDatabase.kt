package com.example.countries.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.countries.data.model.detabaseModel.CountryDb

@Database(entities = [CountryDb::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun countryDao(): CountryDao
}