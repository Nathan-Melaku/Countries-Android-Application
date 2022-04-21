package com.example.countries.data.model.detabaseModel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.countries.data.model.abstractModel.Country
import com.example.countries.data.model.networkModel.CountryNt

@Entity
data class CountryDb (
    @PrimaryKey val id: Int?,
    @Embedded
    override val name: CountryNameDb,
    override val independent: Boolean,
    /*@Ignore
    val capital: List<String>,*/
    override val landLocked: Boolean,
    override val area: Double,
    @Embedded
    override val maps: CountryMapDb,
    override val population: Long,
   /* @Ignore
    val continents: List<String>,*/
    @Embedded
    override val flag: CountryFlagDb
) : Country() {
    companion object{
        fun fromCountryNt(countryNt: CountryNt): CountryDb {
            return CountryDb(
                id = null,
                name = CountryNameDb(countryNt.name.commonName,countryNt.name.officialName),
                independent = countryNt.independent,
                landLocked = countryNt.landLocked,
                area = countryNt.area,
                maps = CountryMapDb(countryNt.maps.google,countryNt.maps.openStreet),
                population = countryNt.population,
                flag = CountryFlagDb(countryNt.flag.png, countryNt.flag.svg)
            )
        }
    }
}