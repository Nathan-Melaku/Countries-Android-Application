package com.example.countries.data.network.dto

import com.example.countries.domain.model.Country
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
