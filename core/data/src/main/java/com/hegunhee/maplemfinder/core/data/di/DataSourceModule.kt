package com.hegunhee.maplemfinder.core.data.di

import com.hegunhee.maplemfinder.core.data.dataSource.local.DefaultLocalSource
import com.hegunhee.maplemfinder.core.data.dataSource.local.LocalDataSource
import com.hegunhee.maplemfinder.core.data.dataSource.remote.DefaultMapleMRemoteDataSource
import com.hegunhee.maplemfinder.core.data.dataSource.remote.MapleMRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideRemoteDataSource(defaultMapleMRemoteDataSource: DefaultMapleMRemoteDataSource) : MapleMRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideLocalDataSource(defaultMapleMLocalDataSource: DefaultLocalSource) : LocalDataSource
}