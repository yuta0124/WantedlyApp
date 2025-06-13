package com.yuta0124.wantedlyapp.core.data.di

import com.yuta0124.wantedlyapp.core.data.database.BookmarkCompanyDao
import com.yuta0124.wantedlyapp.core.data.database.BookmarkCompanyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {
    @Provides
    @Singleton
    fun provideBookmarkCompanyDao(database: BookmarkCompanyDatabase): BookmarkCompanyDao =
        database.bookmarkCompanyDao()
}
