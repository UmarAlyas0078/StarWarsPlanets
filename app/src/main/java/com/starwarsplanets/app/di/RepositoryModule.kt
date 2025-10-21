package com.starwarsplanets.app.di

import com.starwarsplanets.app.data.repository.PlanetRepositoryImpl
import com.starwarsplanets.app.domain.repository.PlanetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPlanetRepository(impl: PlanetRepositoryImpl): PlanetRepository
}