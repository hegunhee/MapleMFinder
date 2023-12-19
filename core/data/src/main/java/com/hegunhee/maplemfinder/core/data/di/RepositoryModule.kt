package com.hegunhee.maplemfinder.core.data.di

import com.hegunhee.maplemfinder.core.data.repository.DefaultRepository
import com.hegunhee.maplemfinder.core.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepository(defaultRepository: DefaultRepository) : Repository

}