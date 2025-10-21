package com.starwarsplanets.app.domain.repository

import androidx.paging.PagingData
import com.starwarsplanets.app.domain.model.PlanetDto
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    suspend fun getPlanets(): Flow<PagingData<PlanetDto>>
}