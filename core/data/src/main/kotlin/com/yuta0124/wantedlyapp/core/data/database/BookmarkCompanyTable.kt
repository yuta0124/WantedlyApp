package com.yuta0124.wantedlyapp.core.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_company_table")
data class BookmarkCompanyTable(
    @PrimaryKey val id: Int,
    @ColumnInfo(defaultValue = "") val title: String,
    @ColumnInfo(defaultValue = "") val companyName: String,
    @ColumnInfo(defaultValue = "1") val canBookMark: Boolean,
    @ColumnInfo(defaultValue = "") val thumbnailUrl: String,
    val companyLogoImage: String?,
)
