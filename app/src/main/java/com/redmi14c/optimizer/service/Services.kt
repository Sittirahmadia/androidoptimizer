package com.redmi14c.optimizer.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.core.app.NotificationCompat
import com.redmi14c.optimizer.MainActivity
import com.redmi14c.optimizer.R

class GameNotificationListener : NotificationListenerService() {

    private val gamePackages = setOf(
        "com.tencent.ig",
        "com.mobile.legends",
        "com.activision.callofduty.shooter",
        "com.miHoYo.GenshinImpact",
        "com.dts.freefireth",
        "com.ea.gp.apexlegendsmobilefps",
        "com.riotgames.league.wildrift",
        "com.miraclegames.farlight84",
        "jp.konami.pesam",
        "com.HoYoverse.hkrpgoversea",
        "com.proximabeta.mf.uamo",
        "com.blizzard.diablo.immortal"
    )

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val packageName = sbn.packageName
        if (gamePackages.contains(packageName)) {
            // Auto activate beast mode when game detected
            sendBroadcast(Intent("com.redmi14c.optimizer.AUTO_TURBO").apply {
                putExtra("package", packageName)
            })
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        // Could deactivate beast mode when game closed
    }
}

class FpsMonitorService : NotificationListenerService() {

    companion object {
        const val CHANNEL_ID = "fps_monitor"
        const val NOTIFICATION_ID = 1001
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, buildNotification())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "FPS Monitor",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("FPS Monitor Active")
            .setContentText("Monitoring game performance...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }
}

class TemperatureMonitorService : NotificationListenerService() {

    companion object {
        const val CHANNEL_ID = "temp_monitor"
        const val NOTIFICATION_ID = 1002
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, buildNotification())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Temperature Monitor",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Temperature Monitor")
            .setContentText("Monitoring CPU/GPU temperature...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()
    }
}

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // Could auto-restart services here
        }
    }
}
