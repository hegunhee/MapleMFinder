package com.hegunhee.maplemfinder.core.data.di

import com.hegunhee.maplemfinder.core.data.dataSource.local.DefaultMapleMLocalSource
import com.hegunhee.maplemfinder.core.data.dataSource.local.MapleMLocalDataSource
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
    abstract fun provideMapleMRemoteDataSource(defaultMapleMRemoteDataSource: DefaultMapleMRemoteDataSource) : MapleMRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideMapleMLocalDataSource(defaultMapleMLocalDataSource: DefaultMapleMLocalSource) : MapleMLocalDataSource
}