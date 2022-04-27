package com.example.countries.data.database.dto

import com.example.countries.data.model.abstractModel.CountryName

data class CountryNameDb(

    override val commonName: String,
    override val officialName: String
) : CountryName()
