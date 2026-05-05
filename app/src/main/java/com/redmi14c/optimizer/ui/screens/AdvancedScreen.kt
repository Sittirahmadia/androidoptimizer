package com.redmi14c.optimizer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun AdvancedScreen(
    navController: NavController,
    viewModel: OptimizerViewModel = viewModel()
) {
    val shizukuStatus by viewModel.shizukuStatus.collectAsStateWithLifecycle()
    var showRebootDialog by remember { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.checkShizukuStatus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Advanced Tools", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
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
                SectionHeader(title = "Advanced Features")
            }

            item {
                AdvancedActionCard(
                    title = "Advanced SetEdit Panel",
                    description = "Tambah custom key-value global/system/secure manual",
                    icon = Icons.Default.Edit,
                    onClick = { navController.navigate("setedit_panel") }
                )
            }

            item {
                AdvancedActionCard(
                    title = "Shizuku Setup Guide",
                    description = "Panduan lengkap setup Shizuku step by step",
                    icon = Icons.Default.Help,
                    onClick = { navController.navigate("shizuku_guide") }
                )
            }

            item {
                AdvancedActionCard(
                    title = "Backup & Restore",
                    description = "Simpan dan pulihkan semua pengaturan tweak",
                    icon = Icons.Default.Save,
                    onClick = { navController.navigate("backup_restore") }
                )
            }

            item {
                AdvancedActionCard(
                    title = "One Tap Reboot",
                    description = "Reboot perangkat dengan konfirmasi",
                    icon = Icons.Default.Refresh,
                    onClick = { showRebootDialog = true },
                    isDanger = true
                )
            }

            item {
                AdvancedActionCard(
                    title = "Reset All Tweaks",
                    description = "Kembalikan semua ke pengaturan default",
                    icon = Icons.Default.Restore,
                    onClick = { showResetDialog = true },
                    isDanger = true
                )
            }

            item {
                SectionHeader(title = "System Info")
            }

            item {
                SystemInfoCard()
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    // Reboot Dialog
    if (showRebootDialog) {
        AlertDialog(
            onDismissRequest = { showRebootDialog = false },
            title = { Text("Konfirmasi Reboot") },
            text = { Text("Apakah Anda yakin ingin mereboot perangkat? Semua perubahan akan diterapkan setelah reboot.") },
            confirmButton = {
                Button(
                    onClick = {
                        if (shizukuStatus == ShizukuStatus.CONNECTED) {
                            viewModel.applyTweaks(listOf("reboot"))
                        }
                        showRebootDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Reboot")
                }
            },
            dismissButton = {
                TextButton(onClick = { showRebootDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

    // Reset Dialog
    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Reset Semua Tweak") },
            text = { Text("Apakah Anda yakin ingin mereset semua tweak ke default? Ini akan mengembalikan semua pengaturan ke nilai awal.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.resetAllTweaks()
                        showResetDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Reset")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }
}

@Composable
fun AdvancedActionCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    isDanger: Boolean = false,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = if (isDanger) 
                MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f)
            else 
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isDanger) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = if (isDanger) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun SystemInfoCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            InfoRow(label = "Device", value = "Redmi 14C")
            InfoRow(label = "SoC", value = "Helio G81 Ultra")
            InfoRow(label = "GPU", value = "Mali-G52 MC2")
            InfoRow(label = "OS", value = "Android 14 / HyperOS")
            InfoRow(label = "RAM", value = "4GB/6GB/8GB LPDDR4X")
            InfoRow(label = "Storage", value = "64GB/128GB eMMC 5.1")
            InfoRow(label = "Display", value = "6.88" 120Hz LCD")
            InfoRow(label = "Battery", value = "5160mAh")
        }
    }
}

@Composable
fun InfoRow(label: String, value: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
