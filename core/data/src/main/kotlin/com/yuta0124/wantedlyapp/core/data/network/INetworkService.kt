package com.yuta0124.wantedlyapp.core.data.network

interface INetworkService {
    /** 募集一覧取得 **/
    suspend fun fetchRecruitments(
        keywork: String?,
        page: Int,
    ): RecruitmentsResponse

    /** 募集詳細取得 **/
    suspend fun fetchRecruitmentDetail(
        id: Int,
    ): RecruitmentDetailResponse
}
