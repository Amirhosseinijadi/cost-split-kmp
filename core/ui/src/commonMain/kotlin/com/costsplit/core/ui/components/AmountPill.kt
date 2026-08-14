package com.costsplit.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AmountPill(
    text: String,
    modifier: Modifier = Modifier,
) {
    val isNegative = text.trim().startsWith("-")
    val backgroundColor = if (isNegative) {
        MaterialTheme.colorScheme.error.copy(alpha = 0.14f)
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }
    val contentColor = if (isNegative) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.onTertiaryContainer
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(6.dp),
            )
            .padding(horizontal = 10.dp, vertical = 5.dp),
    ) {
        Text(
            text = text,
            color = contentColor,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}
