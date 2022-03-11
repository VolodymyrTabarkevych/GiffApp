package ua.tabarkevych.composemvi.remote.dto

import com.google.gson.annotations.SerializedName

data class FixedHeightImageDto(
    @SerializedName("url") val url: String,
    @SerializedName("webp") val webp: String,
    @SerializedName("height") val height: String
)