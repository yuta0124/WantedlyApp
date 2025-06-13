package com.yuta0124.wantedlyapp.core.data.di

import android.content.Context
import androidx.room.Room
import com.yuta0124.wantedlyapp.core.data.database.BookmarkCompanyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideBookmarkCompanyDatabase(@ApplicationContext context: Context): BookmarkCompanyDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = BookmarkCompanyDatabase::class.java,
            name = "bookmark_company_database",
        ).build()
    }
}