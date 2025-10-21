package com.starwarsplanets.app.data.remote

import com.google.gson.annotations.SerializedName

data class Planet(
    @SerializedName("name")
    val name: String,
    @SerializedName("climate")
    val climate: String,
    @SerializedName("orbital_period")
    val orbitalPeriod: String,
    @SerializedName("gravity")
    val gravity: String,
)