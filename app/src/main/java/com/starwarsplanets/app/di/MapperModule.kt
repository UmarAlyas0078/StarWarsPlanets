package com.starwarsplanets.app.di

import com.starwarsplanets.app.data.mapper.PlanetMapper
import com.starwarsplanets.app.data.mapper.PlanetMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    @Singleton
    abstract fun bindPlanetMapper(
        impl: PlanetMapperImpl
    ): PlanetMapper
}