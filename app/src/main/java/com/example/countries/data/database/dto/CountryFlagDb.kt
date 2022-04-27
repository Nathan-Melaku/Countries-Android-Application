package com.example.countries.data.database.dto

import com.example.countries.data.model.abstractModel.CountryFlag

data class CountryFlagDb(

    override  val png: String,
    override  val svg: String
) : CountryFlag()
