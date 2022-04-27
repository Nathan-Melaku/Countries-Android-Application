package com.example.countries.domain.model

abstract class Country {

    abstract val name: CountryName
    abstract val independent: Boolean
    // abstract val capital: List<String>
    abstract val landLocked: Boolean
    abstract val area: Double
    abstract val maps: CountryMap
    abstract val population: Long
    // abstract val continents: List<String>
    abstract val flag: CountryFlag
}