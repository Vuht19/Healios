package com.example.data.di

import com.example.data.database.AppDatabase
import com.example.data.network.service.HealiosService
import com.example.data.repository.HealiosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun provideHealiosRepository(
        healiosService: HealiosService,
        appDatabase: AppDatabase,
        dispatcher: CoroutineDispatcher
    ): HealiosRepository {
        return HealiosRepository(healiosService, appDatabase, dispatcher)
    }
}