package com.starwarsplanets.app.data.remote

import com.google.gson.annotations.SerializedName

data class PlanetResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Planet>
)