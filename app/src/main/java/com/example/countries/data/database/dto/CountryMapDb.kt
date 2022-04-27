package com.example.countries.data.database.dto

import com.example.countries.data.model.abstractModel.CountryMap

data class CountryMapDb(

    override val google: String,
    override val openStreet: String
) : CountryMap()
