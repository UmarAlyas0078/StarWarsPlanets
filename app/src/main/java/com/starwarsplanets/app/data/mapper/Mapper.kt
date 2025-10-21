package com.starwarsplanets.app.data.mapper

import com.starwarsplanets.app.data.remote.Planet
import com.starwarsplanets.app.domain.model.PlanetDto
import javax.inject.Inject
import kotlin.math.absoluteValue

interface PlanetMapper {
    fun mapToDomain(remote: Planet): PlanetDto
}

class PlanetMapperImpl @Inject constructor() : PlanetMapper {
    override fun mapToDomain(remote: Planet): PlanetDto {
        val imageId = remote.name.hashCode().absoluteValue % 1000
        return PlanetDto(
            name = remote.name,
            climate = remote.climate,
            orbitalPeriod = remote.orbitalPeriod,
            gravity = remote.gravity,
            thumbnail = "https://picsum.photos/id/$imageId/200/300",
            highResolution = "https://picsum.photos/id/$imageId/1080/1920"
        )
    }
}