package com.costsplit.feature.groups.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.costsplit.core.ui.components.AmountPill
import com.costsplit.core.ui.components.AvatarBadge
import com.costsplit.core.ui.components.BalanceHero
import com.costsplit.core.ui.components.DongiCard
import com.costsplit.core.ui.components.DongiEmptyState
import com.costsplit.core.ui.components.DongiGlyph
import com.costsplit.core.ui.components.DongiIcon
import com.costsplit.core.ui.components.DongiLoadingRows
import com.costsplit.core.ui.components.DongiScreen
import com.costsplit.core.ui.components.ScreenTitle
import com.costsplit.core.ui.strings.DongiString
import com.costsplit.core.ui.strings.dongiString
import com.costsplit.core.ui.strings.dongiText
import com.costsplit.core.ui.strings.toPersianDigits

@Composable
fun GroupsScreen(
    state: GroupsState,
    onIntent: (GroupsIntent) -> Unit,
) {
    DongiScreen(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            item {
                ScreenTitle(
                    title = dongiString(DongiString.GroupsTitle),
                    subtitle = dongiString(DongiString.GroupsSubtitle),
                    trailing = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            AvatarBadge(label = dongiString(DongiString.MyAvatar), size = 36.dp)
                            Text(
                                text = dongiString(DongiString.MyAccount),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                    },
                )
            }

            item {
                BalanceHero(
                    title = dongiString(DongiString.TotalBalance),
                    amount = totalBalance(state.groups),
                    detail = dongiString(
                        DongiString.ActiveGroupsCount,
                        state.groups.size.toPersianDigits(),
                    ),
                    action = {
                        Button(
                            onClick = { onIntent(GroupsIntent.AddGroupClicked) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = androidx.compose.ui.graphics.Color.White,
                                contentColor = androidx.compose.ui.graphics.Color(0xFF183E36),
                            ),
                        ) {
                            Text(dongiString(DongiString.CreateGroup))
                        }
                    },
                )
            }

            state.errorMessage?.let { message ->
                item {
                    Text(
                        text = dongiText(message),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            if (state.isLoading && state.groups.isEmpty()) {
                item {
                    DongiLoadingRows()
                }
            }

            items(state.groups) { group ->
                GroupRow(group = group, onClick = { onIntent(GroupsIntent.GroupClicked(group.id)) })
            }

            if (!state.isLoading && state.errorMessage == null && state.groups.isEmpty()) {
                item {
                    DongiEmptyState(
                        title = dongiString(DongiString.GroupsEmptyTitle),
                        message = dongiString(DongiString.GroupsEmptyMessage),
                    )
                }
            }
        }
    }
}

@Composable
private fun GroupRow(
    group: GroupUi,
    onClick: () -> Unit,
) {
    DongiCard(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    AvatarBadge(
                        label = group.name.firstOrNull()?.toString().orEmpty(),
                        size = 50.dp,
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = group.name,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = dongiString(
                                DongiString.MembersCount,
                                group.memberCount.toPersianDigits(),
                            ),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AmountPill(group.balance ?: dongiString(DongiString.Settled))
                    DongiGlyph(
                        icon = DongiIcon.Chevron,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        size = 18.dp,
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = dongiText(group.settlement),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                )
                AvatarStack(group)
            }
        }
    }
}

@Composable
private fun AvatarStack(group: GroupUi) {
    val visibleMembers = group.memberBalances.take(2)
    val remaining = (group.memberBalances.size - visibleMembers.size).coerceAtLeast(0)
    Row(horizontalArrangement = Arrangement.spacedBy((-10).dp)) {
        visibleMembers.forEach { member ->
            AvatarBadge(label = member.name.firstOrNull()?.toString().orEmpty(), size = 34.dp)
        }
        if (remaining > 0) {
            AvatarBadge(label = "+${remaining.toPersianDigits()}", size = 34.dp)
        }
    }
}

private fun totalBalance(groups: List<GroupUi>): String {
    val firstPositive = groups.firstOrNull { it.balance?.trim()?.startsWith("+") == true }?.balance
    return firstPositive ?: groups.firstNotNullOfOrNull { it.balance } ?: "+$0.00"
}
