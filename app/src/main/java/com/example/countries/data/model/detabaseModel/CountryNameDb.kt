package com.example.countries.data.model.detabaseModel

import com.example.countries.data.model.abstractModel.CountryName

data class CountryNameDb(

    override val commonName: String,
    override val officialName: String
) : CountryName()
