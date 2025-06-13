package com.yuta0124.wantedlyapp.core.data.repository

import arrow.core.Either
import com.yuta0124.wantedlyapp.core.data.database.BookmarkCompanyDao
import com.yuta0124.wantedlyapp.core.data.database.BookmarkCompanyTable
import com.yuta0124.wantedlyapp.core.data.network.INetworkService
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentDetailResponse
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WantedlyRepository(
    private val networkService: INetworkService,
    private val bookmarkCompanyDao: BookmarkCompanyDao,
    private val ioDispatcher: CoroutineDispatcher,
) : IWantedlyRepository {
    override val bookmarkCompanies: Flow<List<BookmarkCompanyTable>> =
        bookmarkCompanyDao.getBookmarkCompanies()

    override suspend fun fetchRecruitments(
        keyword: String?,
        page: Int
    ): Either<Throwable, RecruitmentsResponse> {
        return withContext(ioDispatcher) {
            return@withContext Either.catch {
                networkService.fetchRecruitments(keyword, page)
            }
        }
    }

    override suspend fun fetchRecruitmentDetail(id: Int): Either<Throwable, RecruitmentDetailResponse> {
        return withContext(ioDispatcher) {
            return@withContext Either.catch {
                networkService.fetchRecruitmentDetail(id)
            }
        }
    }

    override suspend fun insertBookmarkById(id: Int) {
        withContext(ioDispatcher) {
            bookmarkCompanyDao.insertCompany(BookmarkCompanyTable(id = id))
        }
    }

    override suspend fun deleteBookmarkById(id: Int) {
        withContext(ioDispatcher) {
            bookmarkCompanyDao.deleteTargetCompany(BookmarkCompanyTable(id = id))
        }
    }
}
