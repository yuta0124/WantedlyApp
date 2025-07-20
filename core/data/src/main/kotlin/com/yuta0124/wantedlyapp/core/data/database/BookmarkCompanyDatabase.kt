package com.yuta0124.wantedlyapp.core.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BookmarkCompanyTable::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
    ]
)
abstract class BookmarkCompanyDatabase : RoomDatabase() {
    abstract fun bookmarkCompanyDao(): BookmarkCompanyDao
}
