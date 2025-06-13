package com.yuta0124.wantedlyapp.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookmarkCompanyTable::class], version = 1, exportSchema = false)
abstract class BookmarkCompanyDatabase : RoomDatabase() {
    abstract fun bookmarkCompanyDao(): BookmarkCompanyDao
}
