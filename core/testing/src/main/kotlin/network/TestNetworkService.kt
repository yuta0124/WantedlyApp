package network

import com.yuta0124.wantedlyapp.core.data.network.INetworkService
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentDetailResponse
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentsResponse

class TestNetworkService(
    private val fetchRecruitments: (() -> RecruitmentsResponse)? = null,
    private val fetchRecruitmentDetail: (() -> RecruitmentDetailResponse)? = null,
) : INetworkService {
    override suspend fun fetchRecruitments(
        keywork: String?,
        page: Int
    ): RecruitmentsResponse =
        requireNotNull(fetchRecruitments) { "fetchRecruitments is null" }.invoke()

    override suspend fun fetchRecruitmentDetail(id: Int): RecruitmentDetailResponse =
        requireNotNull(fetchRecruitmentDetail) { "fetchRecruitmentDetail is null" }.invoke()
}