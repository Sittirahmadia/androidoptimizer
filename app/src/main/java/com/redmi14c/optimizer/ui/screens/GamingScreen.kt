package com.redmi14c.optimizer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.redmi14c.optimizer.ui.components.*
import com.redmi14c.optimizer.viewmodel.OptimizerViewModel
import com.redmi14c.optimizer.viewmodel.ShizukuStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamingScreen(
    navController: NavController,
    viewModel: OptimizerViewModel = viewModel()
) {
    val shizukuStatus by viewModel.shizukuStatus.collectAsStateWithLifecycle()
    val autoTurbo by viewModel.autoTurbo.collectAsStateWithLifecycle(initialValue = false)
    val fpsOverlay by viewModel.fpsOverlay.collectAsStateWithLifecycle(initialValue = false)
    val tempMonitor by viewModel.tempMonitor.collectAsStateWithLifecycle(initialValue = false)

    val predefinedGames = listOf(
        GameInfo("PUBG Mobile", "com.tencent.ig", "+15 FPS", "High"),
        GameInfo("Mobile Legends", "com.mobile.legends", "+10 FPS", "Medium"),
        GameInfo("COD Mobile", "com.activision.callofduty.shooter", "+12 FPS", "High"),
        GameInfo("Genshin Impact", "com.miHoYo.GenshinImpact", "+8 FPS", "Extreme"),
        GameInfo("Free Fire", "com.dts.freefireth", "+10 FPS", "Medium"),
        GameInfo("Apex Legends", "com.ea.gp.apexlegendsmobilefps", "+12 FPS", "High"),
        GameInfo("League of Legends: Wild Rift", "com.riotgames.league.wildrift", "+10 FPS", "Medium"),
        GameInfo("Farlight 84", "com.miraclegames.farlight84", "+8 FPS", "High"),
        GameInfo("eFootball", "jp.konami.pesam", "+10 FPS", "Medium"),
        GameInfo("Honkai Star Rail", "com.HoYoverse.hkrpgoversea", "+8 FPS", "High"),
        GameInfo("Arena Breakout", "com.proximabeta.mf.uamo", "+12 FPS", "High"),
        GameInfo("Diablo Immortal", "com.blizzard.diablo.immortal", "+8 FPS", "High"),
    )

    LaunchedEffect(Unit) {
        viewModel.checkShizukuStatus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gaming Center", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    IconButton(onClick = { navController.navigate("game_profiles") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Profiles")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                ShizukuStatusCard(
                    isConnected = shizukuStatus == ShizukuStatus.CONNECTED,
                    onGrantPermission = { viewModel.checkShizukuStatus() }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                BeastModeButton(
                    onClick = {
                        if (shizukuStatus == ShizukuStatus.CONNECTED) {
                            viewModel.applyBeastMode()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SectionHeader(title = "Gaming Features")
            }

            item {
                GamingFeatureCard(
                    title = "Auto Game Turbo",
                    description = "Deteksi game otomatis dan aktifkan beast mode",
                    icon = Icons.Default.VideogameAsset,
                    isEnabled = autoTurbo,
                    onToggle = { viewModel.toggleAutoTurbo(it) }
                )
            }

            item {
                GamingFeatureCard(
                    title = "Floating FPS Monitor",
                    description = "Overlay FPS real-time di atas aplikasi",
                    icon = Icons.Default.Speed,
                    isEnabled = fpsOverlay,
                    onToggle = { viewModel.toggleFpsOverlay(it) }
                )
            }

            item {
                GamingFeatureCard(
                    title = "Temperature Monitor",
                    description = "Monitor suhu CPU, GPU, dan baterai real-time",
                    icon = Icons.Default.Thermostat,
                    isEnabled = tempMonitor,
                    onToggle = { viewModel.toggleTempMonitor(it) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(title = "Game Profiles")
            }

            items(predefinedGames) { game ->
                GameProfileCard(
                    game = game,
                    onOptimize = {
                        if (shizukuStatus == ShizukuStatus.CONNECTED) {
                            viewModel.applyBeastMode()
                        }
                    },
                    onCustomize = {
                        navController.navigate("game_profiles")
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

data class GameInfo(
    val name: String,
    val packageName: String,
    val fpsBoost: String,
    val riskLevel: String
)

@Composable
fun GamingFeatureCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = description,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
            Switch(
                checked = isEnabled,
                onCheckedChange = onToggle
            )
        }
    }
}

@Composable
fun GameProfileCard(
    game: GameInfo,
    onOptimize: () -> Unit,
    onCustomize: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = game.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatusChip(
                        text = game.fpsBoost,
                        color = com.redmi14c.optimizer.ui.theme.SuccessGreen,
                        icon = "▲"
                    )
                    StatusChip(
                        text = game.riskLevel,
                        color = when (game.riskLevel) {
                            "Low" -> com.redmi14c.optimizer.ui.theme.SuccessGreen
                            "Medium" -> com.redmi14c.optimizer.ui.theme.WarningYellow
                            "High" -> com.redmi14c.optimizer.ui.theme.BeastOrange
                            "Extreme" -> com.redmi14c.optimizer.ui.theme.ErrorRed
                            else -> com.redmi14c.optimizer.ui.theme.InfoBlue
                        },
                        icon = "⚠"
                    )
                }
            }

            Row {
                TextButton(onClick = onCustomize) {
                    Text("Edit")
                }
                Button(
                    onClick = onOptimize,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Optimize")
                }
            }
        }
    }
}
