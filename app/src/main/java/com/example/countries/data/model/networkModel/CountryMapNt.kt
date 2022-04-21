package com.example.countries.data.model.networkModel

import com.example.countries.data.model.abstractModel.CountryMap
import com.google.gson.annotations.SerializedName

data class CountryMapNt(
    @SerializedName("googleMaps")
    override val google: String,
    @SerializedName("openStreetMaps")
    override val openStreet: String
) : CountryMap()
