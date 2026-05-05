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
import com.redmi14c.optimizer.data.CustomStringEntry
import com.redmi14c.optimizer.shizuku.ShizukuManager
import com.redmi14c.optimizer.ui.components.SectionHeader
import com.redmi14c.optimizer.ui.theme.BeastOrange
import com.redmi14c.optimizer.viewmodel.OptimizerViewModel
import com.redmi14c.optimizer.viewmodel.ShizukuStatus
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetEditPanelScreen(
    navController: NavController,
    viewModel: OptimizerViewModel = viewModel()
) {
    val shizukuStatus by viewModel.shizukuStatus.collectAsStateWithLifecycle()
    val customStrings by viewModel.customStrings.collectAsStateWithLifecycle(initialValue = emptyList())
    val scope = rememberCoroutineScope()

    var showAddDialog by remember { mutableStateOf(false) }
    var showApplyDialog by remember { mutableStateOf(false) }
    var selectedEntry by remember { mutableStateOf<CustomStringEntry?>(null) }
    var applyResult by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.checkShizukuStatus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Advanced SetEdit", fontWeight = FontWeight.Bold) },
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
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "SetEdit Panel",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Tambahkan custom key-value untuk global/system/secure settings. Gunakan dengan hati-hati!",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            item {
                SectionHeader(title = "Custom Entries (${customStrings.size})")
            }

            items(customStrings, key = { it.id }) { entry ->
                CustomStringCard(
                    entry = entry,
                    onDelete = { viewModel.deleteCustomString(entry) },
                    onApply = {
                        selectedEntry = entry
                        showApplyDialog = true
                    }
                )
            }

            if (customStrings.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No custom entries yet. Tap + to add.",
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

    // Add Dialog
    if (showAddDialog) {
        AddCustomStringDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { entry ->
                viewModel.addCustomString(entry)
                showAddDialog = false
            }
        )
    }

    // Apply Dialog
    if (showApplyDialog && selectedEntry != null) {
        AlertDialog(
            onDismissRequest = { 
                showApplyDialog = false
                applyResult = ""
            },
            title = { Text("Apply Custom String") },
            text = {
                Column {
                    Text("Name: ${selectedEntry!!.name}")
                    Text("Command: ${selectedEntry!!.command}")
                    if (applyResult.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = applyResult,
                            color = if (applyResult.contains("Success")) BeastOrange else MaterialTheme.colorScheme.error
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            if (shizukuStatus == ShizukuStatus.CONNECTED) {
                                val result = ShizukuManager.executeCommand(selectedEntry!!.command)
                                applyResult = if (result.success) "Success!" else "Failed: ${result.error}"
                            } else {
                                applyResult = "Shizuku not connected!"
                            }
                        }
                    }
                ) {
                    Text("Apply")
                }
            },
            dismissButton = {
                TextButton(onClick = { 
                    showApplyDialog = false
                    applyResult = ""
                }) {
                    Text("Close")
                }
            }
        )
    }
}

@Composable
fun CustomStringCard(
    entry: CustomStringEntry,
    onDelete: () -> Unit,
    onApply: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
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
                        text = entry.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "${entry.key} = ${entry.value} (${entry.type})",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                    )
                }
                Row {
                    IconButton(onClick = onApply) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Apply",
                            tint = BeastOrange
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
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
            ) {
                Text(
                    text = entry.command,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun AddCustomStringDialog(
    onDismiss: () -> Unit,
    onAdd: (CustomStringEntry) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var key by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("global") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Custom") }

    val types = listOf("global", "system", "secure")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Custom String") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = key,
                    onValueChange = { key = it },
                    label = { Text("Key") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    label = { Text("Value") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Type:",
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
                Row {
                    types.forEach { t ->
                        FilterChip(
                            selected = type == t,
                            onClick = { type = t },
                            label = { Text(t) },
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    }
                }

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description (optional)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && key.isNotBlank()) {
                        val command = when (type) {
                            "global" -> "settings put global $key $value"
                            "system" -> "settings put system $key $value"
                            "secure" -> "settings put secure $key $value"
                            else -> "settings put global $key $value"
                        }
                        onAdd(
                            CustomStringEntry(
                                name = name,
                                key = key,
                                value = value,
                                type = type,
                                description = description,
                                category = category,
                                fpsEstimate = "Custom",
                                batteryImpact = "Custom",
                                riskLevel = "Medium",
                                command = command
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
