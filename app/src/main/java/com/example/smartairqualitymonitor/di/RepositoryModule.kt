package com.example.smartairqualitymonitor.di

import com.example.smartairqualitymonitor.data.api.repository.AirQualityRepository
import com.example.smartairqualitymonitor.domain.repository.IAirQualityRepository
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
    abstract fun bindAirQualityRepository(
        impl: AirQualityRepository
    ): IAirQualityRepository
}
