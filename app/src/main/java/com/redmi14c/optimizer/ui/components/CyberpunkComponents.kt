package com.redmi14c.optimizer.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redmi14c.optimizer.ui.theme.*

// Glassmorphic Card with Neon Glow
@Composable
fun GlassmorphicCard(
    modifier: Modifier = Modifier,
    glowColor: Color = NeonCyan,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = glowColor.copy(alpha = 0.3f),
                spotColor = glowColor.copy(alpha = 0.5f)
            )
            .background(
                color = DarkBgSecondary.copy(alpha = 0.7f),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.5.dp,
                brush = Brush.linearGradient(
                    colors = listOf(glowColor.copy(alpha = 0.5f), glowColor.copy(alpha = 0.2f))
                ),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        content()
    }
}

// Animated Shizuku Status Card
@Composable
fun CyberpunkShizukuStatusCard(
    isRunning: Boolean,
    hasPermission: Boolean,
    modifier: Modifier = Modifier
) {
    val statusColor = if (isRunning && hasPermission) NeonGreen else NeonRed
    val statusText = when {
        !isRunning -> "Shizuku Not Running"
        !hasPermission -> "No Permission"
        else -> "Shizuku Ready"
    }

    GlassmorphicCard(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        glowColor = statusColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = statusColor.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(50)
                    )
                    .border(2.dp, statusColor, RoundedCornerShape(50)),
                contentAlignment = Alignment.Center
            ) {
                if (isRunning && hasPermission) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Status",
                        tint = statusColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = statusText,
                color = DarkText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Animated Toggle Switch with 3D Effect
@Composable
fun CyberpunkToggleSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String = ""
) {
    var isPressed by remember { mutableStateOf(false) }

    val toggleColor by animateColorAsState(
        targetValue = if (checked) NeonGreen else DarkBgTertiary,
        animationSpec = tween(300, easing = EaseInOutCubic),
        label = "toggleColor"
    )

    val glowColor by animateColorAsState(
        targetValue = if (checked) NeonGreen else NeonMagenta,
        animationSpec = tween(300, easing = EaseInOutCubic),
        label = "glowColor"
    )

    val elevation by animateDpAsState(
        targetValue = if (isPressed) 4.dp else 8.dp,
        animationSpec = tween(100),
        label = "elevation"
    )

    Column(modifier = modifier) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                color = DarkText,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(32.dp)
                .shadow(
                    elevation = elevation,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = glowColor.copy(alpha = 0.4f)
                )
                .background(toggleColor, RoundedCornerShape(16.dp))
                .border(1.5.dp, glowColor.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                .clickable {
                    isPressed = true
                    onCheckedChange(!checked)
                }
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            if (event.type == androidx.compose.ui.input.pointer.PointerEventType.Release) {
                                isPressed = false
                            }
                        }
                    }
                },
            contentAlignment = if (checked) Alignment.CenterEnd else Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp)
                    .background(Color.White, RoundedCornerShape(50))
            )
        }
    }
}

// Cyberpunk Tweak Card
@Composable
fun CyberpunkTweakCard(
    title: String,
    description: String,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    GlassmorphicCard(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        glowColor = if (isEnabled) NeonGreen else NeonMagenta
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = DarkText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    color = MutedText,
                    fontSize = 12.sp,
                    maxLines = 2
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Switch(
                checked = isEnabled,
                onCheckedChange = onToggle,
                modifier = Modifier.scale(0.8f)
            )
        }
    }
}

// Animated Section Header
@Composable
fun CyberpunkSectionHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(24.dp)
                    .background(NeonCyan, RoundedCornerShape(2.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = title,
                color = NeonCyan,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.5.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .height(1.dp)
                    .weight(1f)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(NeonCyan.copy(alpha = 0.5f), Color.Transparent)
                        )
                    )
            )
        }
    }
}

// Stat Card for Metrics
@Composable
fun CyberpunkStatCard(
    label: String,
    value: String,
    unit: String = "",
    modifier: Modifier = Modifier
) {
    GlassmorphicCard(
        modifier = modifier
            .width(100.dp)
            .height(90.dp),
        glowColor = NeonPurple
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                color = NeonPurple,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = unit,
                color = NeonPurple.copy(alpha = 0.7f),
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                color = MutedText,
                fontSize = 10.sp,
                maxLines = 1
            )
        }
    }
}

// Gradient Neon Button
@Composable
fun CyberpunkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(12.dp),
                spotColor = NeonCyan.copy(alpha = 0.5f)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = DarkBg
        ),
        contentPadding = PaddingValues(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(NeonCyan, NeonMagenta)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = DarkBg,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

// Extension for scale modifier
fun Modifier.scale(scale: Float) = graphicsLayer(scaleX = scale, scaleY = scale)
