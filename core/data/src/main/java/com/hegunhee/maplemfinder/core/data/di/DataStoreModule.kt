package com.hegunhee.maplemfinder.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    private const val FAVORITE_OCID_DATASTORE_NAME = "FAVORITE_PREFERENCES"
    private const val HISTORY_OCID_DATSTORE_NAME = "HISTORY_PREFERENCES"
    private val Context.favoriteDataStore by preferencesDataStore(FAVORITE_OCID_DATASTORE_NAME)
    private val Context.historyDataStore by preferencesDataStore(HISTORY_OCID_DATSTORE_NAME)

    @Provides
    @Singleton
    @Named("favorite")
    fun provideFavoriteDataStore(
        @ApplicationContext context : Context
    ) : DataStore<Preferences> = context.favoriteDataStore

    @Provides
    @Singleton
    @Named("history")
    fun provideHistoryDataStore(
        @ApplicationContext context : Context
    ) : DataStore<Preferences> = context.historyDataStore
}