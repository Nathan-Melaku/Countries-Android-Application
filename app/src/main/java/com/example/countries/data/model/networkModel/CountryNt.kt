package com.example.countries.data.model.networkModel

import com.example.countries.data.model.abstractModel.Country
import com.google.gson.annotations.SerializedName

data class CountryNt(

    override val name: CountryNameNt,
    override val independent: Boolean,
    // override val capital: List<String>,
    @SerializedName("landlocked")
    override val landLocked: Boolean,
    override val area: Double,
    override val maps: CountryMapNt,
    override val population: Long,
    // override val continents: List<String>,
    @SerializedName("flags")
    override val flag: CountryFlagNt
) : Country()
