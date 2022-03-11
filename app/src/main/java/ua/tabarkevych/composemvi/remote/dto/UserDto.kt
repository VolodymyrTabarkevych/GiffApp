package ua.tabarkevych.composemvi.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("username") val username: String
)