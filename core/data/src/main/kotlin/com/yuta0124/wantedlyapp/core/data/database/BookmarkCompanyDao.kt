package com.yuta0124.wantedlyapp.core.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkCompanyDao {
    @Query("SELECT * FROM bookmark_company_table")
    fun getBookmarkCompanies(): Flow<List<BookmarkCompanyTable>>

    @Insert
    suspend fun insertCompany(target: BookmarkCompanyTable)

    @Delete
    suspend fun deleteTargetCompany(target: BookmarkCompanyTable)
}