package com.example.countries.data.mappers

import com.example.countries.data.database.dto.CountryDb
import com.example.countries.data.database.dto.CountryFlagDb
import com.example.countries.data.database.dto.CountryMapDb
import com.example.countries.data.database.dto.CountryNameDb
import com.example.countries.data.network.dto.CountryFlagNt
import com.example.countries.data.network.dto.CountryMapNt
import com.example.countries.data.network.dto.CountryNameNt
import com.example.countries.data.network.dto.CountryNt
import com.example.countries.domain.model.Country
import com.example.countries.domain.model.CountryFlag
import com.example.countries.domain.model.CountryMap
import com.example.countries.domain.model.CountryName

fun CountryDb.toCountry(countryDb: CountryDb): Country {
    return Country(
        name = CountryName(countryDb.name.commonName, countryDb.name.officialName),
        independent = countryDb.independent,
        landLocked = countryDb.landLocked,
        area = countryDb.area,
        maps = CountryMap(countryDb.maps.google, countryDb.maps.openStreet),
        population = countryDb.population,
        flag = CountryFlag(countryDb.flag.png, countryDb.flag.svg)
    )
}





