package com.redmi14c.optimizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.redmi14c.optimizer.ui.components.BottomNavBar
import com.redmi14c.optimizer.ui.screens.*
import com.redmi14c.optimizer.ui.theme.RedmiOptimizerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RedmiOptimizerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OptimizerApp()
                }
            }
        }
    }
}

@Composable
fun OptimizerApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) { DashboardScreen(navController) }
            composable(Screen.Gaming.route) { GamingScreen(navController) }
            composable(Screen.Tweaks.route) { TweaksScreen(navController) }
            composable(Screen.Advanced.route) { AdvancedScreen(navController) }
            composable(Screen.StringsDB.route) { OptimizerStringsScreen(navController) }
            composable(Screen.Profile.route) { ProfileScreen(navController) }
            composable("setedit_panel") { SetEditPanelScreen(navController) }
            composable("shizuku_guide") { ShizukuGuideScreen(navController) }
            composable("game_profiles") { GameProfilesScreen(navController) }
            composable("backup_restore") { BackupRestoreScreen(navController) }
            composable("about") { AboutScreen(navController) }
        }
    }
}
