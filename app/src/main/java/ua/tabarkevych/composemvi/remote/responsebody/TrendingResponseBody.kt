package ua.tabarkevych.composemvi.remote.responsebody

import com.google.gson.annotations.SerializedName
import ua.tabarkevych.composemvi.remote.dto.GifPostDto
import ua.tabarkevych.composemvi.remote.dto.PaginationDto

data class TrendingResponseBody(
    @SerializedName("data") val data: List<GifPostDto>,
    @SerializedName("pagination") val pagination: PaginationDto
)