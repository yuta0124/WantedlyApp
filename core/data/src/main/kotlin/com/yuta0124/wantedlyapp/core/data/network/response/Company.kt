package com.yuta0124.wantedlyapp.core.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
    @SerialName("address_prefix")
    val addressPrefix: String,
    @SerialName("address_suffix")
    val addressSuffix: String,
    @SerialName("avatar")
    val avatar: Avatar,
    @SerialName("font_color_code")
    val fontColorCode: String,
    @SerialName("founded_on")
    val foundedOn: String,
    @SerialName("founder")
    val founder: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("payroll_number")
    val payrollNumber: Int?,
)