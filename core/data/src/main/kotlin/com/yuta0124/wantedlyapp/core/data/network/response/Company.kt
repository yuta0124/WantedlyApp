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
    val url: String? = null,
    @SerialName("address_prefix")
    val addressPrefix: String? = null,
    @SerialName("address_suffix")
    val addressSuffix: String? = null,
    @SerialName("avatar")
    val avatar: Avatar? = null,
    @SerialName("founded_on")
    val foundedOn: String? = null,
    @SerialName("founder")
    val founder: String? = null,
    @SerialName("latitude")
    val latitude: Double? = null,
    @SerialName("longitude")
    val longitude: Double? = null,
    @SerialName("payroll_number")
    val payrollNumber: Int? = null,
)
