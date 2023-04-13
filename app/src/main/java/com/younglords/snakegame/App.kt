package com.younglords.snakegame

import android.app.Application
import com.funny.data_saver.core.DataSaverConverter
import com.funny.data_saver.core.DataSaverInterface
import com.funny.data_saver.core.DataSaverPreferences
import com.younglords.snakegame.beans.SnakeAssets

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        DataSaverUtils = DataSaverPreferences(this)

        DataSaverConverter.registerTypeConverters(save = SnakeAssets.Saver, restore = SnakeAssets.Restorer)
    }

    companion object {
        lateinit var context : Application
        lateinit var DataSaverUtils : DataSaverInterface
    }
}