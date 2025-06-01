package com.yuta0124.wantedlyapp.core.data.repository

import arrow.core.Either
import com.yuta0124.wantedlyapp.core.data.network.INetworkService
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentDetailResponse
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentsResponse

// TODO: dispatcher
class WantedlyRepository(
    private val networkService: INetworkService,
) : IWantedlyRepository {
    override suspend fun fetchRecruitments(
        keyword: String?,
        page: Int
    ): Either<Throwable, RecruitmentsResponse> = Either.catch {
        networkService.fetchRecruitments(keyword, page)
    }

    override suspend fun fetchRecruitmentDetail(id: Int): Either<Throwable, RecruitmentDetailResponse> =
        Either.catch {
            networkService.fetchRecruitmentDetail(id)
        }
}
