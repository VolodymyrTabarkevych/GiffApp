package ua.tabarkevych.composemvi.data.database.dao

import androidx.room.Dao
import ua.tabarkevych.composemvi.data.entity.GifEntity

@Dao
interface GifDao : BaseDao<GifEntity> {
}