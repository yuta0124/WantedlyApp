package com.yuta0124.wantedlyapp.core.data.di

import com.yuta0124.wantedlyapp.core.common.AppDispatcher
import com.yuta0124.wantedlyapp.core.common.AppDispatchers
import com.yuta0124.wantedlyapp.core.data.network.INetworkService
import com.yuta0124.wantedlyapp.core.data.repository.IWantedlyRepository
import com.yuta0124.wantedlyapp.core.data.repository.WantedlyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideWantedlyRepository(
        networkService: INetworkService,
        @AppDispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher,
    ): IWantedlyRepository = WantedlyRepository(networkService, ioDispatcher)
}
