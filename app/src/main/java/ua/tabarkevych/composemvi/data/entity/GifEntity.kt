package ua.tabarkevych.composemvi.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class GifEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "avatarUrl") val avatarUrl: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "importDatetime") val importDatetime: String,
    @ColumnInfo(name = "fixedHeightImageUrl") val fixedHeightImageUrl: String,
    @ColumnInfo(name = "height") val height: String,
    @ColumnInfo(name = "originalImageUrl") val originalImageUrl: String
)