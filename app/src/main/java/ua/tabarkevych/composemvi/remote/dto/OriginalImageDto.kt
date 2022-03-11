package ua.tabarkevych.composemvi.remote.dto

import com.google.gson.annotations.SerializedName

data class OriginalImageDto(
    @SerializedName("url") val url: String
)