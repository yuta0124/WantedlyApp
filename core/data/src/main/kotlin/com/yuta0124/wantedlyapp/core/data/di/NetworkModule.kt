package com.yuta0124.wantedlyapp.core.data.di

import com.yuta0124.wantedlyapp.core.data.BuildConfig
import com.yuta0124.wantedlyapp.core.data.network.INetworkService
import com.yuta0124.wantedlyapp.core.data.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

private const val TIMEOUT_MILLISECONDS = 30_000

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideKtor(): HttpClient = HttpClient(Android) {
        engine {
            connectTimeout = TIMEOUT_MILLISECONDS
            socketTimeout = TIMEOUT_MILLISECONDS
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                }
            )
        }

        install(Logging) {
            level = LogLevel.ALL
        }

        expectSuccess = true

        defaultRequest {
            url(BuildConfig.BASE_URL)
            contentType(ContentType.Application.Json)
        }
    }

    @Singleton
    @Provides
    fun provideNetworkService(httpClient: HttpClient): INetworkService = NetworkService(httpClient)
}
