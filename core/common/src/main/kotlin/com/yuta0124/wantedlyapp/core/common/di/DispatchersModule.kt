package com.yuta0124.wantedlyapp.core.common.di

import com.yuta0124.wantedlyapp.core.common.AppDispatcher
import com.yuta0124.wantedlyapp.core.common.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {
    @Provides
    @AppDispatcher(AppDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @AppDispatcher(AppDispatchers.DEFAULT)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @AppDispatcher(AppDispatchers.MAIN)
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
