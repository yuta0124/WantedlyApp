import com.yuta0124.wantedlyapp.core.data.toAppError
import com.yuta0124.wantedlyapp.core.model.AppError
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.util.cio.ChannelReadException
import io.mockk.every
import io.mockk.mockk
import java.io.IOException

class ThrowableExtensionTest : FunSpec(
    {
        context("toAppError_BadRequestExceptionが返されること") {
            test("throw_statusCode:400~499") {
                forAll(
                    row(400, AppError.BadRequestException),
                    row(450, AppError.BadRequestException),
                    row(499, AppError.BadRequestException),
                ) { statusCode, expectedAppError ->
                    val mockResponse = mockk<HttpResponse>()
                    val mockStatus = mockk<HttpStatusCode>()
                    every { mockResponse.status } returns mockStatus
                    every { mockStatus.value } returns statusCode

                    val responseException = ResponseException(mockResponse, "Test exception")
                    val result = responseException.toAppError()

                    result shouldBe expectedAppError
                }
            }
        }

        context("toAppError_ServerExceptionが返されること") {
            test("throw_statusCode:500~599") {
                forAll(
                    row(500, AppError.ServerException),
                    row(550, AppError.ServerException),
                    row(599, AppError.ServerException),
                ) { statusCode, expectedAppError ->
                    val mockResponse = mockk<HttpResponse>()
                    val mockStatus = mockk<HttpStatusCode>()
                    every { mockResponse.status } returns mockStatus
                    every { mockStatus.value } returns statusCode

                    val responseException = ResponseException(mockResponse, "Test exception")
                    val result = responseException.toAppError()

                    result shouldBe expectedAppError
                }
            }
        }

        context("toAppError_UnexpectedExceptionが返されること") {
            test("throw_statusCode:4xx,5xx以外") {
                forAll(
                    row(300, AppError.UnexpectedException),
                    row(350, AppError.UnexpectedException),
                    row(399, AppError.UnexpectedException),
                ) { statusCode, expectedAppError ->
                    val mockResponse = mockk<HttpResponse>()
                    val mockStatus = mockk<HttpStatusCode>()
                    every { mockResponse.status } returns mockStatus
                    every { mockStatus.value } returns statusCode

                    val responseException = ResponseException(mockResponse, "Test exception")
                    val result = responseException.toAppError()

                    result shouldBe expectedAppError
                }
            }

            test("throw_Exception") {
                val exception = Exception()
                val result = exception.toAppError()

                result shouldBe AppError.UnexpectedException
            }
        }

        context("toAppError_TimeoutCancellationExceptionが返されること") {
            test("throw_HttpRequestTimeoutException") {
                val request = HttpRequestBuilder().apply {
                    url("https://example.com/test")
                }
                val exception = HttpRequestTimeoutException(request)

                val result = exception.toAppError()

                result shouldBe AppError.TimeoutException
            }

            test("throw_SocketTimeoutException") {
                val exception = SocketTimeoutException("Socket timeout")

                val result = exception.toAppError()

                result shouldBe AppError.TimeoutException
            }
        }

        context("toAppError_NetworkExceptionが返されること") {
            test("throw_ChannelReadException") {
                val exception = ChannelReadException("Channel read error", exception = Exception())

                val result = exception.toAppError()

                result shouldBe AppError.NetworkException
            }

            test("throw_IOException") {
                val exception = IOException()
                val result = exception.toAppError()

                result shouldBe AppError.NetworkException
            }
        }
    }
)
