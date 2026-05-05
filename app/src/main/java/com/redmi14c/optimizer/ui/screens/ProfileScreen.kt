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
import com.redmi14c.optimizer.ui.theme.BeastOrange
import com.redmi14c.optimizer.viewmodel.OptimizerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: OptimizerViewModel = viewModel()
) {
    val activeProfile by viewModel.activeProfile.collectAsStateWithLifecycle(initialValue = "balanced")
    val language by viewModel.language.collectAsStateWithLifecycle(initialValue = "id")
    val darkMode by viewModel.darkMode.collectAsStateWithLifecycle(initialValue = true)
    val materialYou by viewModel.materialYou.collectAsStateWithLifecycle(initialValue = true)

    val profiles = listOf(
        ProfileInfo(
            "balanced",
            "Balanced",
            "Performa seimbang dengan efisiensi baterai",
            "+5 FPS",
            "+10% Battery",
            Icons.Default.Balance,
            BeastOrange.copy(alpha = 0.2f)
        ),
        ProfileInfo(
            "extreme_fps",
            "Extreme FPS",
            "Maksimal FPS untuk gaming kompetitif",
            "+25 FPS",
            "-30% Battery",
            Icons.Default.Speed,
            BeastOrange.copy(alpha = 0.4f)
        ),
        ProfileInfo(
            "battery_saver",
            "Battery Saver",
            "Penghematan baterai maksimal",
            "-5 FPS",
            "+25% Battery",
            Icons.Default.BatteryFull,
            BeastOrange.copy(alpha = 0.1f)
        ),
        ProfileInfo(
            "custom_1",
            "Custom 1",
            "Profil kustom Anda",
            "Variable",
            "Variable",
            Icons.Default.Settings,
            BeastOrange.copy(alpha = 0.15f)
        ),
        ProfileInfo(
            "custom_2",
            "Custom 2",
            "Profil kustom kedua",
            "Variable",
            "Variable",
            Icons.Default.Settings,
            BeastOrange.copy(alpha = 0.15f)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile Manager", fontWeight = FontWeight.Bold) },
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
                SectionHeader(title = "Select Profile")
            }

            items(profiles) { profile ->
                ProfileCard(
                    profile = profile,
                    isSelected = activeProfile == profile.id,
                    onSelect = {
                        viewModel.applyProfile(profile.id)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                SectionHeader(title = "App Settings")
            }

            item {
                SettingsCard(
                    title = "Language",
                    currentValue = if (language == "id") "Indonesia" else "English",
                    onClick = {
                        val newLang = if (language == "id") "en" else "id"
                        viewModel.setLanguage(newLang)
                    }
                )
            }

            item {
                SettingsToggleCard(
                    title = "Dark Mode",
                    isEnabled = darkMode,
                    onToggle = { viewModel.setDarkMode(it) }
                )
            }

            item {
                SettingsToggleCard(
                    title = "Material You Colors",
                    isEnabled = materialYou,
                    onToggle = { viewModel.setMaterialYou(it) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

data class ProfileInfo(
    val id: String,
    val name: String,
    val description: String,
    val fpsEstimate: String,
    val batteryEstimate: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val color: androidx.compose.ui.graphics.Color
)

@Composable
fun ProfileCard(
    profile: ProfileInfo,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onSelect() },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) profile.color else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ),
        border = if (isSelected) {
            androidx.compose.foundation.BorderStroke(2.dp, BeastOrange)
        } else null
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = profile.icon,
                contentDescription = null,
                tint = if (isSelected) BeastOrange else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = profile.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = if (isSelected) BeastOrange else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = profile.description,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatusChip(
                        text = profile.fpsEstimate,
                        color = SuccessGreen,
                        icon = "▲"
                    )
                    StatusChip(
                        text = profile.batteryEstimate,
                        color = if (profile.batteryEstimate.contains("+")) SuccessGreen else WarningYellow,
                        icon = "🔋"
                    )
                }
            }
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    tint = BeastOrange
                )
            }
        }
    }
}

@Composable
fun SettingsCard(
    title: String,
    currentValue: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium
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
            Text(
                text = currentValue,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun SettingsToggleCard(
    title: String,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.medium
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
            Switch(
                checked = isEnabled,
                onCheckedChange = onToggle
            )
        }
    }
}
