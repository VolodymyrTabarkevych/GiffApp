package ua.tabarkevych.composemvi.remote.dto

import com.google.gson.annotations.SerializedName

data class ImagesDto(
    @SerializedName("original") val original: OriginalImageDto,
    @SerializedName("fixed_height") val fixedHeight: FixedHeightImageDto
)