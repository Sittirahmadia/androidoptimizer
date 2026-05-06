package com.redmi14c.optimizer.shizuku

import android.content.pm.PackageManager
import android.os.IBinder
import android.os.RemoteException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rikka.shizuku.Shizuku
import rikka.shizuku.ShizukuBinderWrapper
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader

object ShizukuManager {

    private const val REQUEST_CODE = 1001

    fun isShizukuRunning(): Boolean {
        return try {
            Shizuku.pingBinder()
        } catch (e: Exception) {
            false
        }
    }

    fun hasPermission(): Boolean {
        return if (isShizukuRunning()) {
            Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
    }

    fun requestPermission(listener: Shizuku.OnRequestPermissionResultListener) {
        Shizuku.addRequestPermissionResultListener(listener)
        Shizuku.requestPermission(REQUEST_CODE)
    }

    fun removePermissionListener(listener: Shizuku.OnRequestPermissionResultListener) {
        Shizuku.removeRequestPermissionResultListener(listener)
    }

    fun getUid(): Int {
        return try {
            Shizuku.getUid()
        } catch (e: Exception) {
            -1
        }
    }

    fun isRoot(): Boolean = getUid() == 0
    fun isAdb(): Boolean = getUid() == 2000

    suspend fun executeCommand(command: String): ShellResult = withContext(Dispatchers.IO) {
        try {
            if (!isShizukuRunning() || !hasPermission()) {
                return@withContext ShellResult(
                    success = false,
                    output = "",
                    error = "Shizuku not running or no permission"
                )
            }

            // Use Runtime.exec as fallback since Shizuku.newProcess is deprecated/private
            val process = Runtime.getRuntime().exec(
                arrayOf("sh", "-c", command)
            )

            val output = StringBuilder()
            val error = StringBuilder()

            val outputReader = BufferedReader(InputStreamReader(process.inputStream))
            val errorReader = BufferedReader(InputStreamReader(process.errorStream))

            var line: String?
            while (outputReader.readLine().also { line = it } != null) {
                output.append(line).append("\n")
            }

            while (errorReader.readLine().also { line = it } != null) {
                error.append(line).append("\n")
            }

            val exitCode = process.waitFor()

            ShellResult(
                success = exitCode == 0,
                output = output.toString().trim(),
                error = error.toString().trim(),
                exitCode = exitCode
            )
        } catch (e: Exception) {
            Timber.e(e, "Error executing command: $command")
            ShellResult(
                success = false,
                output = "",
                error = e.message ?: "Unknown error",
                exitCode = -1
            )
        }
    }

    suspend fun executeCommands(commands: List<String>): List<ShellResult> {
        return commands.map { executeCommand(it) }
    }

    suspend fun executeCommandWithOutput(command: String): String {
        val result = executeCommand(command)
        return if (result.success) result.output else result.error
    }

    fun getShizukuVersion(): String {
        return try {
            Shizuku.getBinder()?.let { "v${Shizuku.getVersion()}" } ?: "Not installed"
        } catch (e: Exception) {
            "Unknown"
        }
    }
}

data class ShellResult(
    val success: Boolean,
    val output: String,
    val error: String,
    val exitCode: Int = 0
)
