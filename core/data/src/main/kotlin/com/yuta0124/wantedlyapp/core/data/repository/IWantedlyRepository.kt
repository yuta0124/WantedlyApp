package com.yuta0124.wantedlyapp.core.data.repository

import arrow.core.Either
import com.yuta0124.wantedlyapp.core.data.database.BookmarkCompanyTable
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentDetailResponse
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentsResponse
import kotlinx.coroutines.flow.Flow

interface IWantedlyRepository {
    val bookmarkCompanies: Flow<List<BookmarkCompanyTable>>

    suspend fun fetchRecruitments(
        keyword: String? = null,
        page: Int = 0,
    ): Either<Throwable, RecruitmentsResponse>

    suspend fun fetchRecruitmentDetail(id: Int): Either<Throwable, RecruitmentDetailResponse>

    suspend fun insertBookmarkById(id: Int)
    suspend fun deleteBookmarkById(id: Int)
}
