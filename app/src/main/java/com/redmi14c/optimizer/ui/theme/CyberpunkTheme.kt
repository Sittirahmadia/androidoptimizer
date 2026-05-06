package com.redmi14c.optimizer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Neon Color Palette
val NeonCyan = Color(0xFF00F0FF)
val NeonMagenta = Color(0xFFFF006E)
val NeonPurple = Color(0xFFB400FF)
val NeonPink = Color(0xFFFF10F0)
val NeonGreen = Color(0xFF39FF14)
val NeonYellow = Color(0xFFFFFF00)
val NeonOrange = Color(0xFFFF6600)
val NeonRed = Color(0xFFFF0040)

// Dark Background
val DarkBg = Color(0xFF0A0E27)
val DarkBgSecondary = Color(0xFF131B3D)
val DarkBgTertiary = Color(0xFF1A2555)

// Neutrals
val DarkText = Color(0xFFF0F0F0)
val MutedText = Color(0xFFB0B0B0)

val CyberpunkLightColorScheme = lightColorScheme(
    primary = NeonCyan,
    onPrimary = DarkBg,
    primaryContainer = NeonCyan.copy(alpha = 0.1f),
    onPrimaryContainer = NeonCyan,
    secondary = NeonMagenta,
    onSecondary = Color.White,
    secondaryContainer = NeonMagenta.copy(alpha = 0.1f),
    onSecondaryContainer = NeonMagenta,
    tertiary = NeonPurple,
    onTertiary = Color.White,
    tertiaryContainer = NeonPurple.copy(alpha = 0.1f),
    onTertiaryContainer = NeonPurple,
    error = NeonRed,
    onError = Color.White,
    errorContainer = NeonRed.copy(alpha = 0.1f),
    onErrorContainer = NeonRed,
    background = DarkBg,
    onBackground = DarkText,
    surface = DarkBgSecondary,
    onSurface = DarkText,
    surfaceVariant = DarkBgTertiary,
    onSurfaceVariant = MutedText,
)

val CyberpunkDarkColorScheme = darkColorScheme(
    primary = NeonCyan,
    onPrimary = DarkBg,
    primaryContainer = NeonCyan.copy(alpha = 0.1f),
    onPrimaryContainer = NeonCyan,
    secondary = NeonMagenta,
    onSecondary = DarkBg,
    secondaryContainer = NeonMagenta.copy(alpha = 0.1f),
    onSecondaryContainer = NeonMagenta,
    tertiary = NeonPurple,
    onTertiary = DarkBg,
    tertiaryContainer = NeonPurple.copy(alpha = 0.1f),
    onTertiaryContainer = NeonPurple,
    error = NeonRed,
    onError = DarkBg,
    errorContainer = NeonRed.copy(alpha = 0.1f),
    onErrorContainer = NeonRed,
    background = DarkBg,
    onBackground = DarkText,
    surface = DarkBgSecondary,
    onSurface = DarkText,
    surfaceVariant = DarkBgTertiary,
    onSurfaceVariant = MutedText,
)

@Composable
fun CyberpunkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> CyberpunkDarkColorScheme
        else -> CyberpunkLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
