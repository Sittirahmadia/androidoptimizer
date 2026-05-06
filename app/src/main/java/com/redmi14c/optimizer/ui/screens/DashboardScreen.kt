package com.redmi14c.optimizer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.redmi14c.optimizer.ui.components.*
import com.redmi14c.optimizer.ui.theme.BeastOrange
import com.redmi14c.optimizer.viewmodel.OptimizerViewModel
import com.redmi14c.optimizer.viewmodel.ShizukuStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: OptimizerViewModel = viewModel()
) {
    val shizukuStatus by viewModel.shizukuStatus.collectAsStateWithLifecycle()
    val activeTweaks by viewModel.activeTweaksCount.collectAsStateWithLifecycle()
    val beastMode by viewModel.beastMode.collectAsStateWithLifecycle(initialValue = false)
    val applyResult by viewModel.applyResult.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.checkShizukuStatus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "14C Beast Optimizer",
                        fontWeight = FontWeight.Bold
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    IconButton(onClick = { navController.navigate("about") }) {
                        Icon(Icons.Default.Info, contentDescription = "About")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    InfoCard(
                        title = "Active Tweaks",
                        value = activeTweaks.toString(),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Tune,
                                contentDescription = null,
                                tint = BeastOrange,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                    InfoCard(
                        title = "Est. FPS Boost",
                        value = "+${activeTweaks * 2}",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Speed,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                SectionHeader(title = "Quick Actions")
            }

            items(listOf("Gaming", "Tweaks", "Advanced", "Strings DB", "Profile")) { action ->
                QuickActionCard(
                    title = action,
                    onClick = {
                        when (action) {
                            "Gaming" -> navController.navigate("gaming")
                            "Tweaks" -> navController.navigate("tweaks")
                            "Advanced" -> navController.navigate("advanced")
                            "Strings DB" -> navController.navigate("strings_db")
                            "Profile" -> navController.navigate("profile")
                        }
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    // Show result snackbar
    applyResult?.let { result ->
        val message = when (result) {
            is com.redmi14c.optimizer.viewmodel.ApplyResult.Success -> result.message
            is com.redmi14c.optimizer.viewmodel.ApplyResult.PartialSuccess -> result.message
            is com.redmi14c.optimizer.viewmodel.ApplyResult.Error -> result.message
        }
        val success = result is com.redmi14c.optimizer.viewmodel.ApplyResult.Success

        LaunchedEffect(result) {
            viewModel.clearApplyResult()
        }
    }
}

@Composable
fun QuickActionCard(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}
