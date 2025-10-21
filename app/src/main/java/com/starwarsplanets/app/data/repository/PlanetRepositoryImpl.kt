package com.starwarsplanets.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.starwarsplanets.app.data.dataSource.PlanetPagingSource
import com.starwarsplanets.app.data.mapper.PlanetMapper
import com.starwarsplanets.app.data.remote.PlanetApi
import com.starwarsplanets.app.domain.model.PlanetDto
import com.starwarsplanets.app.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val api: PlanetApi,
    private val mapper: PlanetMapper
) : PlanetRepository {
    override suspend fun getPlanets(): Flow<PagingData<PlanetDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { PlanetPagingSource(api) }
        ).flow.map { pagingData ->
            pagingData.map { dto ->
                mapper.mapToDomain(dto)
            }
        }
    }
}