package com.example.countries.data.network.dto

import com.example.countries.domain.model.CountryName
import com.google.gson.annotations.SerializedName

data class CountryNameNt(

    @SerializedName("common")
    override val commonName: String,
    @SerializedName("official")
    override val officialName: String
) : CountryName()
