package com.example.countries.data.model.detabaseModel

import com.example.countries.data.model.abstractModel.CountryFlag

data class CountryFlagDb(

    override  val png: String,
    override  val svg: String
) : CountryFlag()
