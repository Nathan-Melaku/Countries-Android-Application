package com.example.countries.data.network.dto

import com.example.countries.domain.model.CountryFlag

data class CountryFlagNt(

    override val png: String,
    override val svg: String
) : CountryFlag()