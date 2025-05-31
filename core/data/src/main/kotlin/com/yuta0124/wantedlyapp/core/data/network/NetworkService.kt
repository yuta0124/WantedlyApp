package com.yuta0124.wantedlyapp.core.data.network

import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentDetailResponse
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class NetworkService(private val httpClient: HttpClient) : INetworkService {
    override suspend fun fetchRecruitments(
        keywork: String?,
        page: Int,
    ): RecruitmentsResponse = httpClient.get("projects") {
        parameter("q", keywork)
        parameter("page", page)
    }.body()

    override suspend fun fetchRecruitmentDetail(id: Int): RecruitmentDetailResponse =
        httpClient.get("projects") {
            url(id.toString())
        }.body()
}
