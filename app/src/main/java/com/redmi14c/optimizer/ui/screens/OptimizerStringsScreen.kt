package com.redmi14c.optimizer.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.redmi14c.optimizer.data.OptimizerString
import com.redmi14c.optimizer.data.OptimizerStringsDatabase
import com.redmi14c.optimizer.shizuku.ShizukuManager
import com.redmi14c.optimizer.ui.components.SectionHeader
import com.redmi14c.optimizer.ui.components.StatusChip
import com.redmi14c.optimizer.ui.theme.*
import com.redmi14c.optimizer.viewmodel.OptimizerViewModel
import com.redmi14c.optimizer.viewmodel.ShizukuStatus
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptimizerStringsScreen(
    navController: NavController,
    viewModel: OptimizerViewModel = viewModel()
) {
    val shizukuStatus by viewModel.shizukuStatus.collectAsStateWithLifecycle()
    val clipboardManager = LocalClipboardManager.current
    val scope = rememberCoroutineScope()

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var showAddDialog by remember { mutableStateOf(false) }
    var showApplyDialog by remember { mutableStateOf(false) }
    var selectedString by remember { mutableStateOf<OptimizerString?>(null) }
    var applyResult by remember { mutableStateOf("") }

    val categories = listOf("All") + OptimizerStringsDatabase.getCategories()

    val filteredStrings = remember(searchQuery, selectedCategory) {
        if (searchQuery.isEmpty() && selectedCategory == "All") {
            OptimizerStringsDatabase.ALL_STRINGS
        } else if (searchQuery.isNotEmpty()) {
            OptimizerStringsDatabase.search(searchQuery)
        } else {
            OptimizerStringsDatabase.getByCategory(selectedCategory)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.checkShizukuStatus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Optimizer Strings DB", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Custom")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Cari string optimizer...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear")
                        }
                    }
                },
                singleLine = true,
                shape = MaterialTheme.shapes.large
            )

            // Category Chips
            ScrollableCategoryChips(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )

            // Results count
            Text(
                text = "${filteredStrings.size} strings found",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )

            // Strings List
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredStrings, key = { it.id }) { string ->
                    OptimizerStringCard(
                        string = string,
                        onCopy = {
                            val text = string.command ?: "${string.key}=${string.value}"
                            clipboardManager.setText(AnnotatedString(text))
                        },
                        onApply = {
                            selectedString = string
                            showApplyDialog = true
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }

    // Apply Dialog
    if (showApplyDialog && selectedString != null) {
        AlertDialog(
            onDismissRequest = { showApplyDialog = false },
            title = { Text("Apply String") },
            text = {
                Column {
                    Text("String: ${selectedString!!.name}")
                    Text("Key: ${selectedString!!.key}")
                    Text("Value: ${selectedString!!.value}")
                    Text("Type: ${selectedString!!.type}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = selectedString!!.command ?: "setprop ${selectedString!!.key} ${selectedString!!.value}",
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    if (applyResult.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = applyResult,
                            color = if (applyResult.contains("Success")) SuccessGreen else ErrorRed,
                            fontSize = 12.sp
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            if (shizukuStatus == ShizukuStatus.CONNECTED) {
                                val cmd = selectedString!!.command ?: "setprop ${selectedString!!.key} ${selectedString!!.value}"
                                val result = ShizukuManager.executeCommand(cmd)
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
fun ScrollableCategoryChips(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            val selected = category == selectedCategory
            FilterChip(
                selected = selected,
                onClick = { onCategorySelected(category) },
                label = { Text(category) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = BeastOrange.copy(alpha = 0.2f),
                    selectedLabelColor = BeastOrange
                )
            )
        }
    }
}

@Composable
fun OptimizerStringCard(
    string: OptimizerString,
    onCopy: () -> Unit,
    onApply: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable { expanded = !expanded },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = string.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "${string.key} = ${string.value}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                    )
                }

                Row {
                    IconButton(onClick = onCopy) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    IconButton(onClick = onApply) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Apply",
                            modifier = Modifier.size(20.dp),
                            tint = BeastOrange
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                StatusChip(
                    text = string.category,
                    color = InfoBlue,
                    icon = "📁"
                )
                StatusChip(
                    text = "${string.fpsBoost} FPS",
                    color = SuccessGreen,
                    icon = "▲"
                )
                StatusChip(
                    text = "${string.batteryImpact}%",
                    color = if (string.batteryImpact >= 0) SuccessGreen else WarningYellow,
                    icon = "🔋"
                )
                StatusChip(
                    text = string.riskLevel,
                    color = when (string.riskLevel.lowercase()) {
                        "low" -> SuccessGreen
                        "medium" -> WarningYellow
                        "high" -> BeastOrange
                        "extreme" -> ErrorRed
                        else -> InfoBlue
                    },
                    icon = "⚠"
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Text(
                        text = "Description:",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = string.description,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Command:",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    SelectionContainer {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = string.command ?: "setprop ${string.key} ${string.value}",
                                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
