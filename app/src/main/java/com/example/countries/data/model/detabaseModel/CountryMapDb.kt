package com.example.countries.data.model.detabaseModel

import com.example.countries.data.model.abstractModel.CountryMap

data class CountryMapDb(

    override val google: String,
    override val openStreet: String
) : CountryMap()
