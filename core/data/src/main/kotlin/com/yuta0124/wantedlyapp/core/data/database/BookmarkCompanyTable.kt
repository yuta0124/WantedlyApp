package com.yuta0124.wantedlyapp.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_company_table")
data class BookmarkCompanyTable(
    @PrimaryKey val id: Int,
)
