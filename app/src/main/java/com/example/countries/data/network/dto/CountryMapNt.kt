package com.example.countries.data.network.dto

import com.example.countries.domain.model.CountryMap
import com.google.gson.annotations.SerializedName

data class CountryMapNt(
    @SerializedName("googleMaps")
    override val google: String,
    @SerializedName("openStreetMaps")
    override val openStreet: String
) : CountryMap()
