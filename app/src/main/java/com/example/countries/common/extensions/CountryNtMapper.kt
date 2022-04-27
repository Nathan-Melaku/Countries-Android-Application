package com.example.countries.data.mappers

import com.example.countries.data.network.dto.CountryNt
import com.example.countries.domain.model.Country
import com.example.countries.domain.model.CountryFlag
import com.example.countries.domain.model.CountryMap
import com.example.countries.domain.model.CountryName

fun CountryNt.toCountry(countryNt: CountryNt): Country {
    return Country(
        name = CountryName(countryNt.name.commonName,countryNt.name.officialName),
        independent = countryNt.independent,
        landLocked = countryNt.landLocked,
        area = countryNt.area,
        maps = CountryMap(countryNt.maps.google, countryNt.maps.openStreet),
        population = countryNt.population,
        flag = CountryFlag(countryNt.flag.png, countryNt.flag.svg)
    )
}