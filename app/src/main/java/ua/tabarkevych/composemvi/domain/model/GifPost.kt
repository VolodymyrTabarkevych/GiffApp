package ua.tabarkevych.composemvi.domain.model

data class GifPost(
    val id: String,
    val avatarUrl: String,
    val username: String,
    val title: String,
    val importDatetime: String,
    val fixedHeightImageUrl: String,
    val height: String,
    val originalImageUrl: String
)