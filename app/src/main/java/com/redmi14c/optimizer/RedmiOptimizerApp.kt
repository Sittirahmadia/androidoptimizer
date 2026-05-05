package com.redmi14c.optimizer

import android.app.Application
import android.content.Context
import com.redmi14c.optimizer.data.AppDatabase
import com.redmi14c.optimizer.data.TweakDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import rikka.shizuku.ShizukuProvider

class RedmiOptimizerApp : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    lateinit var database: AppDatabase
        private set

    lateinit var tweakDataStore: TweakDataStore
        private set

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        ShizukuProvider.enableMultiProcessSupport(false)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        database = AppDatabase.getDatabase(this)
        tweakDataStore = TweakDataStore(this)
    }

    companion object {
        lateinit var instance: RedmiOptimizerApp
            private set
    }
}
