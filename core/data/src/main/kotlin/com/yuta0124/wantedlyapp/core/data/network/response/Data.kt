package com.yuta0124.wantedlyapp.core.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: Image,
    @SerialName("title")
    val title: String,
    @SerialName("company")
    val company: Company,
    @SerialName("description")
    val description: String?,
    @SerialName("can_bookmark")
    val canBookmark: Boolean,
    @SerialName("can_support")
    val canSupport: Boolean?,
    @SerialName("candidate_count")
    val candidateCount: Int?,
    @SerialName("category_message")
    val categoryMessage: String?,
    @SerialName("leader")
    val leader: Leader?,
    @SerialName("location")
    val location: String?,
    @SerialName("location_suffix")
    val locationSuffix: String?,
    @SerialName("looking_for")
    val lookingFor: String?,
    @SerialName("page_view")
    val pageView: Int?,
    @SerialName("published_at")
    val publishedAt: String?,
    @SerialName("staffings")
    val staffings: List<Staffing>?,
    @SerialName("staffings_count")
    val staffingsCount: Int?,
    @SerialName("support_count")
    val supportCount: Int?,
    @SerialName("supported")
    val supported: Boolean?,
    @SerialName("use_webview")
    val useWebview: Boolean?,
    @SerialName("video_available")
    val videoAvailable: Boolean?,
)
