package ua.tabarkevych.composemvi.remote.dto

import com.google.gson.annotations.SerializedName

data class GifPostDto(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("title") val title: String,
    @SerializedName("import_datetime") val importDatetime: String,
    @SerializedName("images") val images: ImagesDto,
    @SerializedName("user") val user: UserDto?
)