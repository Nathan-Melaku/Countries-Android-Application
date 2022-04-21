package com.example.countries.data.model.networkModel

import com.example.countries.data.model.abstractModel.CountryName
import com.google.gson.annotations.SerializedName

data class CountryNameNt(

    @SerializedName("common")
    override val commonName: String,
    @SerializedName("official")
    override val officialName: String
) : CountryName()
