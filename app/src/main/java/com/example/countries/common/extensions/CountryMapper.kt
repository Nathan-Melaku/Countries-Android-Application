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

fun Country.toCountryDb(country: Country): CountryDb {
    return CountryDb(
        id = null,
        name = CountryNameDb(country.name.commonName, country.name.officialName),
        independent = country.independent,
        landLocked = country.landLocked,
        area = country.area,
        maps = CountryMapDb(country.maps.google, country.maps.openStreet),
        population = country.population,
        flag = CountryFlagDb(country.flag.png, country.flag.svg)
    )
}

fun Country.toCountryNt(country: Country): CountryNt {
    return CountryNt(
        name = CountryNameNt(country.name.commonName,country.name.officialName),
        independent = country.independent,
        landLocked = country.landLocked,
        area = country.area,
        maps = CountryMapNt(country.maps.google, country.maps.openStreet),
        population = country.population,
        flag = CountryFlagNt(country.flag.png, country.flag.svg)
    )
}