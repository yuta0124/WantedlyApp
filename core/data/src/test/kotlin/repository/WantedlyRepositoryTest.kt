package repository

import arrow.core.Either
import com.yuta0124.wantedlyapp.core.data.network.response.Company
import com.yuta0124.wantedlyapp.core.data.network.response.DetailData
import com.yuta0124.wantedlyapp.core.data.network.response.Image
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentDetailResponse
import com.yuta0124.wantedlyapp.core.data.network.response.RecruitmentsResponse
import com.yuta0124.wantedlyapp.core.data.repository.WantedlyRepository
import database.TestBookmarkCompanyDar
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.testCoroutineScheduler
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import network.TestNetworkService

@OptIn(ExperimentalStdlibApi::class, ExperimentalCoroutinesApi::class)
class WantedlyRepositoryTest : FunSpec(
    {
        context("レスポンスのstatusCoddが200の時") {
            test("fetchRecruitments_Either.Rightで値が返されること").config(
                coroutineTestScope = true
            ) {
                val fakeNetworkService = TestNetworkService(
                    fetchRecruitments = {
                        RecruitmentsResponse(
                            data = listOf(),
                            metadata = null,
                        )
                    }
                )
                val repository = WantedlyRepository(
                    fakeNetworkService,
                    bookmarkCompanyDao = TestBookmarkCompanyDar(),
                    ioDispatcher = UnconfinedTestDispatcher(scheduler = testCoroutineScheduler),
                )

                val actual = repository.fetchRecruitments()
                val expected = Either.Right(
                    RecruitmentsResponse(
                        data = listOf(),
                        metadata = null,
                    )
                )

                actual shouldBe expected
            }

            test("fetchRecruitmentDetail_statusCode:200_Either.Rightで値が返されること").config(
                coroutineTestScope = true
            ) {
                val data = DetailData(
                    id = 0,
                    title = "",
                    image = Image(original = ""),
                    company = Company(id = 0, name = ""),
                    whatDescription = "",
                    whyDescription = "",
                    whoDescription = "",
                )
                val fakeNetworkService = TestNetworkService(
                    fetchRecruitmentDetail = {
                        RecruitmentDetailResponse(data = data)
                    }
                )
                val repository = WantedlyRepository(
                    fakeNetworkService,
                    bookmarkCompanyDao = TestBookmarkCompanyDar(),
                    ioDispatcher = UnconfinedTestDispatcher(scheduler = testCoroutineScheduler),
                )

                val actual = repository.fetchRecruitmentDetail(0)
                val expected = Either.Right(
                    RecruitmentDetailResponse(data = data)
                )

                actual shouldBe expected
            }
        }

        context("レスポンスのstatusCodeが200以外のエラー時") {
            test("fetchRecruitments_Either.Leftで値が返されること").config(
                coroutineTestScope = true,
            ) {
                val fakeNetworkService = TestNetworkService(
                    fetchRecruitments = {
                        throw Exception()
                    }
                )

                val repository = WantedlyRepository(
                    fakeNetworkService,
                    bookmarkCompanyDao = TestBookmarkCompanyDar(),
                    ioDispatcher = UnconfinedTestDispatcher(scheduler = testCoroutineScheduler),
                )

                val actual = repository.fetchRecruitments()

                actual.isLeft().shouldBeTrue()
            }


            test("fetchRecruitmentDetail_Either.Leftで値が返されること").config(
                coroutineTestScope = true,
            ) {
                val fakeNetworkService = TestNetworkService(
                    fetchRecruitmentDetail = {
                        throw Exception()
                    }
                )

                val repository = WantedlyRepository(
                    fakeNetworkService,
                    bookmarkCompanyDao = TestBookmarkCompanyDar(),
                    ioDispatcher = UnconfinedTestDispatcher(scheduler = testCoroutineScheduler),
                )

                val actual = repository.fetchRecruitmentDetail(0)

                actual.isLeft().shouldBeTrue()
            }
        }
    }
)