package com.starwarsplanets.app.data.remote
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetApi {
    @GET("planets/")
    suspend fun getPlants(@Query("page") page: Int): PlanetResponse
}