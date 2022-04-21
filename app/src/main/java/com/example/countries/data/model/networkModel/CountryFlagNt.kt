package com.example.countries.data.model.networkModel

import com.example.countries.data.model.abstractModel.CountryFlag

data class CountryFlagNt(

    override val png: String,
    override val svg: String
) : CountryFlag()