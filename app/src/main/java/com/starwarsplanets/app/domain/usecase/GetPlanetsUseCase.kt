package com.starwarsplanets.app.domain.usecase

import androidx.paging.PagingData
import com.starwarsplanets.app.domain.model.PlanetDto
import com.starwarsplanets.app.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(private val planetRepository: PlanetRepository) {
    suspend operator fun invoke(): Flow<PagingData<PlanetDto>> {
        return planetRepository.getPlanets()
    }
}