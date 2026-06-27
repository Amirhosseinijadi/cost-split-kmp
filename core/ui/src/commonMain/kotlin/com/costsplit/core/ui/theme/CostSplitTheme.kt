package com.costsplit.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CostSplitColors = lightColorScheme(
    primary = Color(0xFF256A5D),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD7F3EB),
    onPrimaryContainer = Color(0xFF0C332B),
    secondary = Color(0xFF8B5E34),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFE0BD),
    onSecondaryContainer = Color(0xFF301B08),
    tertiary = Color(0xFF5567A8),
    onTertiary = Color.White,
    background = Color(0xFFF8F7F2),
    onBackground = Color(0xFF1F2523),
    surface = Color(0xFFFFFCF7),
    onSurface = Color(0xFF1F2523),
    surfaceVariant = Color(0xFFE6E2D8),
    onSurfaceVariant = Color(0xFF626960),
    outline = Color(0xFFCAC6BC),
)

@Composable
fun CostSplitTheme(
    colorScheme: ColorScheme = CostSplitColors,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content,
    )
}
