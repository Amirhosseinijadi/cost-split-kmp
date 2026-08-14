package com.costsplit.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.costsplit.core.ui.components.BalanceHero
import com.costsplit.core.ui.components.DongiCard
import com.costsplit.core.ui.components.DongiEmptyState
import com.costsplit.core.ui.components.DongiGlyph
import com.costsplit.core.ui.components.DongiIcon
import com.costsplit.core.ui.components.DongiLoadingRows
import com.costsplit.core.ui.components.DongiScreen
import com.costsplit.core.ui.components.MetricCard
import com.costsplit.core.ui.components.QuickAction
import com.costsplit.core.ui.components.SectionHeader
import com.costsplit.core.ui.components.ScreenTitle
import com.costsplit.core.ui.strings.DongiString
import com.costsplit.core.ui.strings.dongiString
import com.costsplit.core.ui.strings.dongiText
import com.costsplit.core.ui.strings.toPersianDigits

@Composable
fun HomeScreen(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit,
) {
    DongiScreen(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            item {
                ScreenTitle(
                    title = dongiString(DongiString.HomeGreeting),
                    subtitle = dongiString(DongiString.HomeSubtitle),
                    trailing = {
                        AvatarBadge(label = dongiString(DongiString.HomeAvatar), size = 44.dp)
                    },
                )
            }

            item {
                BalanceHero(
                    title = dongiString(DongiString.HomeReceivable),
                    amount = "+ ${state.amountOwedBack}",
                    detail = dongiString(DongiString.FriendsCount, state.friendCount.toPersianDigits()),
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    MetricCard(
                        title = dongiString(DongiString.HomeYouOwe),
                        value = state.amountYouOwe,
                        detail = dongiString(DongiString.GroupsCount, state.groupCount.toPersianDigits()),
                        modifier = Modifier.weight(1f),
                    )
                    MetricCard(
                        title = dongiString(DongiString.HomeOwedToYou),
                        value = state.amountOwedBack,
                        detail = dongiString(DongiString.FriendsCount, state.friendCount.toPersianDigits()),
                        modifier = Modifier.weight(1f),
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    QuickAction(
                        title = dongiString(DongiString.HomeNewGroup),
                        icon = DongiIcon.Groups,
                        onClick = { onIntent(HomeIntent.CreateGroupClicked) },
                    )
                    QuickAction(
                        title = dongiString(DongiString.HomeActivity),
                        icon = DongiIcon.Activity,
                        onClick = { onIntent(HomeIntent.ActivityClicked) },
                    )
                    QuickAction(
                        title = dongiString(DongiString.HomeRefresh),
                        icon = DongiIcon.Settle,
                        onClick = { onIntent(HomeIntent.Refresh) },
                    )
                }
            }

            item {
                SectionHeader(
                    title = dongiString(DongiString.HomeRecentGroups),
                    action = dongiString(DongiString.SeeAll),
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

            if (state.isLoading && state.recentGroups.isEmpty()) {
                item {
                    DongiLoadingRows()
                }
            }

            items(state.recentGroups) { group ->
                HomeGroupRow(group)
            }

            if (!state.isLoading && state.errorMessage == null && state.recentGroups.isEmpty()) {
                item {
                    DongiEmptyState(
                        title = dongiString(DongiString.HomeEmptyTitle),
                        message = dongiString(DongiString.HomeEmptyMessage),
                    )
                }
            }

            item {
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun HomeGroupRow(group: HomeGroupUi) {
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
                AvatarBadge(label = group.name.firstOrNull()?.toString().orEmpty())
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = group.name,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = dongiString(DongiString.MembersCount, group.memberCount.toPersianDigits()),
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
    }
}
