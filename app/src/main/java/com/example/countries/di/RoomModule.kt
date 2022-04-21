package com.example.countries.di

import android.content.Context
import androidx.room.Room
import com.example.countries.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesCountryDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "Country_db"
        ).build()

    @Singleton
    @Provides
    fun providesCountryDao(db: AppDatabase) = db.countryDao()
}