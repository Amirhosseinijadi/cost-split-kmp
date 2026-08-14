package com.costsplit.feature.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.costsplit.core.ui.components.AmountPill
import com.costsplit.core.ui.components.AvatarBadge
import com.costsplit.core.ui.components.DongiCard
import com.costsplit.core.ui.components.DongiEmptyState
import com.costsplit.core.ui.components.DongiGlyph
import com.costsplit.core.ui.components.DongiIcon
import com.costsplit.core.ui.components.DongiLoadingRows
import com.costsplit.core.ui.components.DongiScreen
import com.costsplit.core.ui.components.SectionHeader
import com.costsplit.core.ui.components.ScreenTitle

@Composable
fun ActivityScreen(
    state: ActivityState,
    onIntent: (ActivityIntent) -> Unit,
) {
    DongiScreen(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            item {
                ScreenTitle(
                    title = state.title,
                    subtitle = state.subtitle,
                )
            }

            item {
                SectionHeader(title = "تازه‌ترین تغییرات")
            }

            state.errorMessage?.let { message ->
                item {
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            if (state.isLoading && state.activities.isEmpty()) {
                item {
                    DongiLoadingRows()
                }
            }

            items(state.activities) { activity ->
                ActivityRow(activity)
            }

            if (!state.isLoading && state.errorMessage == null && state.activities.isEmpty()) {
                item {
                    DongiEmptyState(
                        title = "هنوز فعالیتی نیست",
                        message = "با ثبت اولین هزینه، تغییرات گروه اینجا دیده می‌شوند.",
                    )
                }
            }
        }
    }
}

@Composable
private fun ActivityRow(activity: ActivityUi) {
    DongiCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                androidx.compose.material3.Surface(
                    shape = androidx.compose.foundation.shape.CircleShape,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                ) {
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier.padding(10.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        DongiGlyph(DongiIcon.Receipt, MaterialTheme.colorScheme.primary, size = 20.dp)
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = activity.title,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "${activity.group} • ${activity.date}",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
            AmountPill(activity.amount)
        }
    }
}
