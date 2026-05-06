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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.redmi14c.optimizer.ui.components.SectionHeader
import com.redmi14c.optimizer.ui.theme.BeastOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShizukuGuideScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shizuku Setup Guide", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
                GuideStepCard(
                    step = 1,
                    title = "Install Shizuku App",
                    description = "Download dan install Shizuku dari Play Store atau GitHub (github.com/RikkaApps/Shizuku-API).",
                    icon = Icons.Default.Download
                )
            }

            item {
                GuideStepCard(
                    step = 2,
                    title = "Start Shizuku via Wireless Debugging",
                    description = "Buka Settings > Developer Options > Wireless Debugging. Aktifkan dan catat pairing code.",
                    icon = Icons.Default.Wifi
                )
            }

            item {
                GuideStepCard(
                    step = 3,
                    title = "Pair Shizuku",
                    description = "Buka Shizuku app > Start via Wireless Debugging. Masukkan pairing code dari langkah 2.",
                    icon = Icons.Default.Link
                )
            }

            item {
                GuideStepCard(
                    step = 4,
                    title = "Grant Permission",
                    description = "Buka 14C Beast Optimizer. App akan meminta izin Shizuku. Tap 'Allow' untuk memberikan akses shell.",
                    icon = Icons.Default.Security
                )
            }

            item {
                GuideStepCard(
                    step = 5,
                    title = "Ready to Optimize!",
                    description = "Status Shizuku akan menunjukkan 'Connected'. Sekarang Anda bisa menerapkan semua tweak!",
                    icon = Icons.Default.CheckCircle
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(title = "Tips")
            }

            item {
                TipCard(
                    title = "ADB over WiFi Alternative",
                    description = "Jika wireless debugging tidak tersedia, gunakan 'adb tcpip 5555' dari PC, lalu connect via WiFi."
                )
            }

            item {
                TipCard(
                    title = "Shizuku Auto-Start",
                    description = "Shizuku perlu di-start ulang setiap reboot. Gunakan aplikasi automation seperti Tasker untuk auto-start."
                )
            }

            item {
                TipCard(
                    title = "Root Alternative",
                    description = "Jika device sudah rooted, Shizuku akan otomatis menggunakan root privilege yang lebih powerful."
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun GuideStepCard(
    step: Int,
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
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
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = BeastOrange.copy(alpha = 0.2f)
            ) {
                Text(
                    text = step.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = BeastOrange,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun TipCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
