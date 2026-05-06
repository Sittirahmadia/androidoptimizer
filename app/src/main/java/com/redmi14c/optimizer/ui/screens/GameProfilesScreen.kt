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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.redmi14c.optimizer.data.GameProfile
import com.redmi14c.optimizer.ui.components.SectionHeader
import com.redmi14c.optimizer.ui.theme.BeastOrange
import com.redmi14c.optimizer.viewmodel.OptimizerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameProfilesScreen(
    navController: NavController,
    viewModel: OptimizerViewModel = viewModel()
) {
    val gameProfiles by viewModel.gameProfiles.collectAsStateWithLifecycle(initialValue = emptyList())
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Game Profiles", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
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
                SectionHeader(title = "Per-App Game Profiles")
            }

            items(gameProfiles, key = { it.id }) { profile ->
                GameProfileEditCard(
                    profile = profile,
                    onDelete = { viewModel.deleteGameProfile(profile) }
                )
            }

            if (gameProfiles.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No custom profiles yet. Tap + to add.",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    if (showAddDialog) {
        AddGameProfileDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { profile ->
                viewModel.addGameProfile(profile)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun GameProfileEditCard(
    profile: GameProfile,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = profile.gameName,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = profile.packageName,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProfileAttributeChip(label = "DPI", value = "${profile.dpi}")
                ProfileAttributeChip(label = "Governor", value = profile.cpuGovernor)
                ProfileAttributeChip(label = "Refresh", value = "${profile.refreshRate}Hz")
            }
        }
    }
}

@Composable
fun ProfileAttributeChip(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = BeastOrange.copy(alpha = 0.15f)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$label: ",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Text(
                text = value,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = BeastOrange
            )
        }
    }
}

@Composable
fun AddGameProfileDialog(
    onDismiss: () -> Unit,
    onAdd: (GameProfile) -> Unit
) {
    var gameName by remember { mutableStateOf("") }
    var packageName by remember { mutableStateOf("") }
    var dpi by remember { mutableStateOf("320") }
    var refreshRate by remember { mutableStateOf("120") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Game Profile") },
        text = {
            Column {
                OutlinedTextField(
                    value = gameName,
                    onValueChange = { gameName = it },
                    label = { Text("Game Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = packageName,
                    onValueChange = { packageName = it },
                    label = { Text("Package Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = dpi,
                    onValueChange = { dpi = it },
                    label = { Text("DPI") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = refreshRate,
                    onValueChange = { refreshRate = it },
                    label = { Text("Refresh Rate (Hz)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (gameName.isNotBlank() && packageName.isNotBlank()) {
                        onAdd(
                            GameProfile(
                                gameName = gameName,
                                packageName = packageName,
                                dpi = dpi.toIntOrNull() ?: 320,
                                refreshRate = refreshRate.toIntOrNull() ?: 120,
                                isCustom = true
                            )
                        )
                    }
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
