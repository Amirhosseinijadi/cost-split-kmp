package com.costsplit.feature.groups.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.costsplit.core.ui.components.DongiScreen
import com.costsplit.core.ui.components.SectionHeader
import com.costsplit.core.ui.components.ScreenTitle
import com.costsplit.feature.groups.presentation.GroupExpenseUi
import com.costsplit.feature.groups.presentation.GroupUi
import com.costsplit.feature.groups.presentation.MemberBalanceUi
import com.costsplit.core.ui.strings.DongiString
import com.costsplit.core.ui.strings.dongiString
import com.costsplit.core.ui.strings.dongiText
import com.costsplit.core.ui.strings.toPersianDigits

@Composable
fun GroupDetailsScreen(
    group: GroupUi?,
    expenses: List<GroupExpenseUi>,
) {
    DongiScreen(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            item {
                ScreenTitle(
                    title = group?.name ?: dongiString(DongiString.GroupDetailsTitle),
                    subtitle = dongiString(
                        DongiString.GroupSubtitle,
                        group?.let {
                            dongiString(DongiString.MembersCount, it.memberCount.toPersianDigits())
                        } ?: dongiString(DongiString.WithoutMembers),
                        group?.currency ?: "USD",
                    ),
                )
            }

            item {
                BalanceHero(
                    title = dongiString(DongiString.GroupYourBalance),
                    amount = group?.balance ?: dongiString(DongiString.Settled),
                    detail = group?.settlement?.let { dongiText(it) }
                        ?: dongiString(DongiString.SettlementUnavailable),
                    action = {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = androidx.compose.ui.graphics.Color.White,
                                contentColor = androidx.compose.ui.graphics.Color(0xFF183E36),
                            ),
                        ) {
                            Text(dongiString(DongiString.AddExpense))
                        }
                    },
                )
            }

            item {
                SectionHeader(title = dongiString(DongiString.MembersAndBalances))
            }

            item {
                val members = group?.memberBalances.orEmpty()
                if (members.isEmpty()) {
                    DongiEmptyState(
                        title = dongiString(DongiString.NoBalanceTitle),
                        message = dongiString(DongiString.NoBalanceMessage),
                    )
                } else {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(members) { member -> MemberCard(member) }
                    }
                }
            }

            item {
                SectionHeader(title = dongiString(DongiString.RecentExpenses))
            }

            items(expenses) { expense ->
                ExpenseRow(expense)
            }

            if (expenses.isEmpty()) {
                item {
                    DongiEmptyState(
                        title = dongiString(DongiString.NoExpenseTitle),
                        message = dongiString(DongiString.NoExpenseMessage),
                    )
                }
            }
        }
    }
}

@Composable
private fun MemberCard(
    member: MemberBalanceUi,
) {
    DongiCard(
        modifier = Modifier.width(128.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 14.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AvatarBadge(label = member.name.firstOrNull()?.toString().orEmpty(), size = 34.dp)
            Text(
                text = member.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = member.amount,
                color = if (member.amount.trim().startsWith("-")) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.tertiary
                },
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@Composable
private fun ExpenseRow(expense: GroupExpenseUi) {
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
                        text = expense.title,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = expenseMeta(expense),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
            AmountPill(expense.amount)
        }
    }
}

@Composable
private fun expenseMeta(expense: GroupExpenseUi): String {
    val category = when (expense.category.lowercase()) {
        "food" -> dongiString(DongiString.CategoryFood)
        "transport" -> dongiString(DongiString.CategoryTransport)
        "travel" -> dongiString(DongiString.CategoryTravel)
        "shopping" -> dongiString(DongiString.CategoryShopping)
        else -> dongiString(DongiString.CategoryGeneral)
    }
    return listOfNotNull(
        expense.paidBy.takeIf { it.isNotBlank() }?.let {
            dongiString(DongiString.PaidBy, it)
        },
        category,
        expense.date.takeIf { it.isNotBlank() },
    ).joinToString(dongiString(DongiString.MetadataSeparator))
}
