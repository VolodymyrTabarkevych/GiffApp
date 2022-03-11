package ua.tabarkevych.composemvi.data.mapper

import ua.tabarkevych.composemvi.domain.model.GifPost
import ua.tabarkevych.composemvi.remote.dto.GifPostDto
import ua.tabarkevych.composemvi.util.EMPTY_STRING

fun GifPostDto.toDomain() = GifPost(
    id = id,
    avatarUrl = user?.avatarUrl ?: EMPTY_STRING,
    username = user?.username ?: EMPTY_STRING,
    title = title,
    importDatetime = importDatetime,
    fixedHeightImageUrl = images.fixedHeight.webp,
    height = images.fixedHeight.height,
    originalImageUrl = images.original.url
)