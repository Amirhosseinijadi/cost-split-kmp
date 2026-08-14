package com.costsplit.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cost_split_kmp.core.ui.generated.resources.Res
import cost_split_kmp.core.ui.generated.resources.vazirmatn_regular
import cost_split_kmp.core.ui.generated.resources.vazirmatn_semibold
import org.jetbrains.compose.resources.Font

private val DongiLightColors = lightColorScheme(
    primary = Color(0xFF176B5B),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDCEFEA),
    onPrimaryContainer = Color(0xFF123E35),
    secondary = Color(0xFF485A55),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE7EFEC),
    onSecondaryContainer = Color(0xFF263B35),
    tertiary = Color(0xFF177A59),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFE0F2E9),
    onTertiaryContainer = Color(0xFF15513E),
    error = Color(0xFFB84C4C),
    errorContainer = Color(0xFFFFE8E5),
    background = Color(0xFFF7F7F4),
    onBackground = Color(0xFF202421),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF202421),
    surfaceVariant = Color(0xFFF0F1ED),
    onSurfaceVariant = Color(0xFF717771),
    outline = Color(0xFFE5E7E2),
    outlineVariant = Color(0xFFF0F1ED),
)

private val DongiDarkColors = darkColorScheme(
    primary = Color(0xFF77C8B6),
    onPrimary = Color(0xFF08251F),
    primaryContainer = Color(0xFF173E36),
    onPrimaryContainer = Color(0xFFDCEFEA),
    secondary = Color(0xFFABC8C0),
    onSecondary = Color(0xFF071C1A),
    secondaryContainer = Color(0xFF103D38),
    onSecondaryContainer = Color(0xFFD5FBF5),
    tertiary = Color(0xFF4ADE80),
    onTertiary = Color(0xFF07170C),
    tertiaryContainer = Color(0xFF12351F),
    onTertiaryContainer = Color(0xFFDCFCE7),
    error = Color(0xFFF87171),
    background = Color(0xFF111412),
    onBackground = Color(0xFFF2F4F0),
    surface = Color(0xFF191D1A),
    onSurface = Color(0xFFF2F4F0),
    surfaceVariant = Color(0xFF242925),
    onSurfaceVariant = Color(0xFFA8AFA9),
    outline = Color(0xFF303630),
    outlineVariant = Color(0xFF262C27),
)

@Composable
private fun dongiTypography(): Typography {
    val vazirmatn = FontFamily(
        Font(Res.font.vazirmatn_regular, FontWeight.Normal),
        Font(Res.font.vazirmatn_semibold, FontWeight.SemiBold),
        Font(Res.font.vazirmatn_semibold, FontWeight.Bold),
    )
    return Typography(
    headlineMedium = TextStyle(
        fontFamily = vazirmatn,
        fontSize = 28.sp,
        lineHeight = 38.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = vazirmatn,
        fontSize = 24.sp,
        lineHeight = 34.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = vazirmatn,
        fontSize = 19.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = vazirmatn,
        fontSize = 16.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = vazirmatn,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = vazirmatn,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = vazirmatn,
        fontSize = 13.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = vazirmatn,
        fontSize = 11.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp,
    ),
    )
}

private val DongiShapes = Shapes(
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    small = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(18.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(22.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(28.dp),
)

@Composable
fun CostSplitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorScheme: ColorScheme = if (darkTheme) DongiDarkColors else DongiLightColors,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = dongiTypography(),
        shapes = DongiShapes,
        content = content,
    )
}
