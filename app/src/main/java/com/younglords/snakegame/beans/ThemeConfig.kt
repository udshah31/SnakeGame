package com.younglords.snakegame.beans

import androidx.compose.runtime.MutableState
import com.funny.data_saver.core.mutableDataSaverStateOf
import com.younglords.snakegame.App.Companion.DataSaverUtils

object ThemeConfig {
    val themeList = listOf(
        SnakeAssets.SnakeAssets1, SnakeAssets.SnakeAssets2
    )
    val savedSnakeAssets: MutableState<SnakeAssets> = mutableDataSaverStateOf(DataSaverUtils ,key = "saved_snake_assets", initialValue = SnakeAssets.SnakeAssets1)
}