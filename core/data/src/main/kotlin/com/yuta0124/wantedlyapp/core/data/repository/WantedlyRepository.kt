package com.yuta0124.wantedlyapp.core.data.repository

import arrow.core.Either
import com.yuta0124.wantedlyapp.core.data.network.INetworkService
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentDetailResponse
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

// TODO: dispatcher
class WantedlyRepository(
    private val networkService: INetworkService,
    private val ioDispatcher: CoroutineDispatcher,
) : IWantedlyRepository {
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
}
