import com.yuta0124.wantedlyapp.core.data.toAppError
import com.yuta0124.wantedlyapp.core.model.AppError
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
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
            withData(
                Pair(400, AppError.BadRequestException),
                Pair(450, AppError.BadRequestException),
                Pair(499, AppError.BadRequestException),
            ) { (statusCode, expectedAppError) ->
                val mockResponse = mockk<HttpResponse>()
                val mockStatus = mockk<HttpStatusCode>()
                every { mockResponse.status } returns mockStatus
                every { mockStatus.value } returns statusCode

                val responseException = ResponseException(mockResponse, "Test exception")
                val actual = responseException.toAppError()

                actual shouldBe expectedAppError
            }
        }

        context("toAppError_ServerExceptionが返されること") {
            withData(
                Pair(500, AppError.ServerException),
                Pair(550, AppError.ServerException),
                Pair(599, AppError.ServerException),
            ) { (statusCode, expectedAppError) ->
                val mockResponse = mockk<HttpResponse>()
                val mockStatus = mockk<HttpStatusCode>()
                every { mockResponse.status } returns mockStatus
                every { mockStatus.value } returns statusCode

                val responseException = ResponseException(mockResponse, "Test exception")
                val actual = responseException.toAppError()

                actual shouldBe expectedAppError
            }
        }

        context("toAppError_UnexpectedExceptionが返されること") {
            withData(
                Pair(300, AppError.UnexpectedException),
                Pair(350, AppError.UnexpectedException),
                Pair(399, AppError.UnexpectedException),
            ) { (statusCode, expectedAppError) ->
                val mockResponse = mockk<HttpResponse>()
                val mockStatus = mockk<HttpStatusCode>()
                every { mockResponse.status } returns mockStatus
                every { mockStatus.value } returns statusCode

                val responseException = ResponseException(mockResponse, "Test exception")
                val actual = responseException.toAppError()

                actual shouldBe expectedAppError
            }

            test("throw_Exception") {
                val exception = Exception()
                val actual = exception.toAppError()

                actual shouldBe AppError.UnexpectedException
            }
        }

        context("toAppError_TimeoutCancellationExceptionが返されること") {
            test("throw_HttpRequestTimeoutException") {
                val request = HttpRequestBuilder().apply {
                    url("https://example.com/test")
                }
                val exception = HttpRequestTimeoutException(request)

                val actual = exception.toAppError()

                actual shouldBe AppError.TimeoutException
            }

            test("throw_SocketTimeoutException") {
                val exception = SocketTimeoutException("Socket timeout")

                val actual = exception.toAppError()

                actual shouldBe AppError.TimeoutException
            }
        }

        context("toAppError_NetworkExceptionが返されること") {
            test("throw_ChannelReadException") {
                val exception = ChannelReadException("Channel read error", exception = Exception())

                val actual = exception.toAppError()

                actual shouldBe AppError.NetworkException
            }

            test("throw_IOException") {
                val exception = IOException()
                val actual = exception.toAppError()

                actual shouldBe AppError.NetworkException
            }
        }
    }
)
