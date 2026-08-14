package com.costsplit.core.ui.strings

import androidx.compose.runtime.Composable
import com.costsplit.core.common.result.ApiError
import cost_split_kmp.core.ui.generated.resources.Res
import cost_split_kmp.core.ui.generated.resources.account_preferences
import cost_split_kmp.core.ui.generated.resources.active_groups_count
import cost_split_kmp.core.ui.generated.resources.activity_added_expense
import cost_split_kmp.core.ui.generated.resources.activity_empty_message
import cost_split_kmp.core.ui.generated.resources.activity_empty_title
import cost_split_kmp.core.ui.generated.resources.activity_metadata
import cost_split_kmp.core.ui.generated.resources.activity_subtitle
import cost_split_kmp.core.ui.generated.resources.activity_title
import cost_split_kmp.core.ui.generated.resources.add_expense
import cost_split_kmp.core.ui.generated.resources.app_name
import cost_split_kmp.core.ui.generated.resources.category_food
import cost_split_kmp.core.ui.generated.resources.category_general
import cost_split_kmp.core.ui.generated.resources.category_shopping
import cost_split_kmp.core.ui.generated.resources.category_transport
import cost_split_kmp.core.ui.generated.resources.category_travel
import cost_split_kmp.core.ui.generated.resources.create_group
import cost_split_kmp.core.ui.generated.resources.currency_us_dollar
import cost_split_kmp.core.ui.generated.resources.default_currency
import cost_split_kmp.core.ui.generated.resources.equal_split
import cost_split_kmp.core.ui.generated.resources.error_network
import cost_split_kmp.core.ui.generated.resources.error_no_user
import cost_split_kmp.core.ui.generated.resources.error_server
import cost_split_kmp.core.ui.generated.resources.error_unauthorized
import cost_split_kmp.core.ui.generated.resources.error_unknown
import cost_split_kmp.core.ui.generated.resources.every_friday
import cost_split_kmp.core.ui.generated.resources.friends_count
import cost_split_kmp.core.ui.generated.resources.group_details_title
import cost_split_kmp.core.ui.generated.resources.group_member_fallback
import cost_split_kmp.core.ui.generated.resources.group_subtitle
import cost_split_kmp.core.ui.generated.resources.group_your_balance
import cost_split_kmp.core.ui.generated.resources.groups_count
import cost_split_kmp.core.ui.generated.resources.groups_empty_message
import cost_split_kmp.core.ui.generated.resources.groups_empty_title
import cost_split_kmp.core.ui.generated.resources.groups_subtitle
import cost_split_kmp.core.ui.generated.resources.groups_title
import cost_split_kmp.core.ui.generated.resources.home_activity
import cost_split_kmp.core.ui.generated.resources.home_avatar
import cost_split_kmp.core.ui.generated.resources.home_empty_message
import cost_split_kmp.core.ui.generated.resources.home_empty_title
import cost_split_kmp.core.ui.generated.resources.home_greeting
import cost_split_kmp.core.ui.generated.resources.home_new_group
import cost_split_kmp.core.ui.generated.resources.home_owed_to_you
import cost_split_kmp.core.ui.generated.resources.home_receivable
import cost_split_kmp.core.ui.generated.resources.home_recent_groups
import cost_split_kmp.core.ui.generated.resources.home_refresh
import cost_split_kmp.core.ui.generated.resources.home_subtitle
import cost_split_kmp.core.ui.generated.resources.home_you_owe
import cost_split_kmp.core.ui.generated.resources.members_and_balances
import cost_split_kmp.core.ui.generated.resources.members_count
import cost_split_kmp.core.ui.generated.resources.metadata_separator
import cost_split_kmp.core.ui.generated.resources.my_account
import cost_split_kmp.core.ui.generated.resources.my_avatar
import cost_split_kmp.core.ui.generated.resources.nav_activity
import cost_split_kmp.core.ui.generated.resources.nav_groups
import cost_split_kmp.core.ui.generated.resources.nav_home
import cost_split_kmp.core.ui.generated.resources.nav_settings
import cost_split_kmp.core.ui.generated.resources.no_balance_message
import cost_split_kmp.core.ui.generated.resources.no_balance_title
import cost_split_kmp.core.ui.generated.resources.no_expense_message
import cost_split_kmp.core.ui.generated.resources.no_expense_title
import cost_split_kmp.core.ui.generated.resources.notifications
import cost_split_kmp.core.ui.generated.resources.notifications_subtitle
import cost_split_kmp.core.ui.generated.resources.paid_by
import cost_split_kmp.core.ui.generated.resources.payment_reminder
import cost_split_kmp.core.ui.generated.resources.recent_expenses
import cost_split_kmp.core.ui.generated.resources.recent_updates
import cost_split_kmp.core.ui.generated.resources.see_all
import cost_split_kmp.core.ui.generated.resources.settled
import cost_split_kmp.core.ui.generated.resources.settlement_complete
import cost_split_kmp.core.ui.generated.resources.settlement_member_pays
import cost_split_kmp.core.ui.generated.resources.settlement_pays_you
import cost_split_kmp.core.ui.generated.resources.settlement_unavailable
import cost_split_kmp.core.ui.generated.resources.settlement_you_pay
import cost_split_kmp.core.ui.generated.resources.settings_subtitle
import cost_split_kmp.core.ui.generated.resources.settings_title
import cost_split_kmp.core.ui.generated.resources.split_method
import cost_split_kmp.core.ui.generated.resources.total_balance
import cost_split_kmp.core.ui.generated.resources.unknown_screen
import cost_split_kmp.core.ui.generated.resources.without_members
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.StringResource

enum class DongiString {
    AppName, NavHome, NavGroups, NavActivity, NavSettings, UnknownScreen,
    ErrorNetwork, ErrorUnauthorized, ErrorServer, ErrorUnknown, ErrorNoUser,
    HomeGreeting, HomeSubtitle, HomeAvatar, HomeReceivable, HomeYouOwe, HomeOwedToYou,
    HomeNewGroup, HomeActivity, HomeRefresh, HomeRecentGroups, SeeAll, HomeEmptyTitle,
    HomeEmptyMessage, GroupsCount, FriendsCount, MembersCount, Settled,
    GroupsTitle, GroupsSubtitle, MyAccount, MyAvatar, TotalBalance, ActiveGroupsCount,
    CreateGroup, GroupsEmptyTitle, GroupsEmptyMessage, SettlementUnavailable,
    SettlementComplete, GroupMemberFallback, SettlementYouPay, SettlementPaysYou,
    SettlementMemberPays, GroupDetailsTitle, WithoutMembers, GroupSubtitle,
    GroupYourBalance, AddExpense, MembersAndBalances, NoBalanceTitle, NoBalanceMessage,
    RecentExpenses, NoExpenseTitle, NoExpenseMessage, CategoryFood, CategoryTransport,
    CategoryTravel, CategoryShopping, CategoryGeneral, PaidBy, MetadataSeparator,
    ActivityTitle, ActivitySubtitle, RecentUpdates, ActivityEmptyTitle,
    ActivityEmptyMessage, ActivityAddedExpense, ActivityMetadata, SettingsTitle,
    SettingsSubtitle, Notifications, NotificationsSubtitle, AccountPreferences,
    DefaultCurrency, CurrencyUsDollar, SplitMethod, EqualSplit, PaymentReminder, EveryFriday,
}

data class DongiText(
    val key: DongiString,
    val arguments: List<Any> = emptyList(),
)

@Composable
fun dongiString(key: DongiString, vararg arguments: Any): String =
    stringResource(key.resource(), *arguments)

@Composable
fun dongiText(text: DongiText): String = dongiString(text.key, *text.arguments.toTypedArray())

suspend fun getDongiString(key: DongiString, vararg arguments: Any): String =
    getString(key.resource(), *arguments)

fun ApiError.toDongiText(): DongiText = when (this) {
    ApiError.Network -> DongiText(DongiString.ErrorNetwork)
    ApiError.Unauthorized -> DongiText(DongiString.ErrorUnauthorized)
    is ApiError.Server -> DongiText(DongiString.ErrorServer, listOf(code))
    is ApiError.Unknown -> DongiText(DongiString.ErrorUnknown)
}

fun Int.toPersianDigits(): String = toString().map { character ->
    if (character.isDigit()) "۰۱۲۳۴۵۶۷۸۹"[character.digitToInt()] else character
}.joinToString("")

private fun DongiString.resource(): StringResource = when (this) {
    DongiString.AppName -> Res.string.app_name
    DongiString.NavHome -> Res.string.nav_home
    DongiString.NavGroups -> Res.string.nav_groups
    DongiString.NavActivity -> Res.string.nav_activity
    DongiString.NavSettings -> Res.string.nav_settings
    DongiString.UnknownScreen -> Res.string.unknown_screen
    DongiString.ErrorNetwork -> Res.string.error_network
    DongiString.ErrorUnauthorized -> Res.string.error_unauthorized
    DongiString.ErrorServer -> Res.string.error_server
    DongiString.ErrorUnknown -> Res.string.error_unknown
    DongiString.ErrorNoUser -> Res.string.error_no_user
    DongiString.HomeGreeting -> Res.string.home_greeting
    DongiString.HomeSubtitle -> Res.string.home_subtitle
    DongiString.HomeAvatar -> Res.string.home_avatar
    DongiString.HomeReceivable -> Res.string.home_receivable
    DongiString.HomeYouOwe -> Res.string.home_you_owe
    DongiString.HomeOwedToYou -> Res.string.home_owed_to_you
    DongiString.HomeNewGroup -> Res.string.home_new_group
    DongiString.HomeActivity -> Res.string.home_activity
    DongiString.HomeRefresh -> Res.string.home_refresh
    DongiString.HomeRecentGroups -> Res.string.home_recent_groups
    DongiString.SeeAll -> Res.string.see_all
    DongiString.HomeEmptyTitle -> Res.string.home_empty_title
    DongiString.HomeEmptyMessage -> Res.string.home_empty_message
    DongiString.GroupsCount -> Res.string.groups_count
    DongiString.FriendsCount -> Res.string.friends_count
    DongiString.MembersCount -> Res.string.members_count
    DongiString.Settled -> Res.string.settled
    DongiString.GroupsTitle -> Res.string.groups_title
    DongiString.GroupsSubtitle -> Res.string.groups_subtitle
    DongiString.MyAccount -> Res.string.my_account
    DongiString.MyAvatar -> Res.string.my_avatar
    DongiString.TotalBalance -> Res.string.total_balance
    DongiString.ActiveGroupsCount -> Res.string.active_groups_count
    DongiString.CreateGroup -> Res.string.create_group
    DongiString.GroupsEmptyTitle -> Res.string.groups_empty_title
    DongiString.GroupsEmptyMessage -> Res.string.groups_empty_message
    DongiString.SettlementUnavailable -> Res.string.settlement_unavailable
    DongiString.SettlementComplete -> Res.string.settlement_complete
    DongiString.GroupMemberFallback -> Res.string.group_member_fallback
    DongiString.SettlementYouPay -> Res.string.settlement_you_pay
    DongiString.SettlementPaysYou -> Res.string.settlement_pays_you
    DongiString.SettlementMemberPays -> Res.string.settlement_member_pays
    DongiString.GroupDetailsTitle -> Res.string.group_details_title
    DongiString.WithoutMembers -> Res.string.without_members
    DongiString.GroupSubtitle -> Res.string.group_subtitle
    DongiString.GroupYourBalance -> Res.string.group_your_balance
    DongiString.AddExpense -> Res.string.add_expense
    DongiString.MembersAndBalances -> Res.string.members_and_balances
    DongiString.NoBalanceTitle -> Res.string.no_balance_title
    DongiString.NoBalanceMessage -> Res.string.no_balance_message
    DongiString.RecentExpenses -> Res.string.recent_expenses
    DongiString.NoExpenseTitle -> Res.string.no_expense_title
    DongiString.NoExpenseMessage -> Res.string.no_expense_message
    DongiString.CategoryFood -> Res.string.category_food
    DongiString.CategoryTransport -> Res.string.category_transport
    DongiString.CategoryTravel -> Res.string.category_travel
    DongiString.CategoryShopping -> Res.string.category_shopping
    DongiString.CategoryGeneral -> Res.string.category_general
    DongiString.PaidBy -> Res.string.paid_by
    DongiString.MetadataSeparator -> Res.string.metadata_separator
    DongiString.ActivityTitle -> Res.string.activity_title
    DongiString.ActivitySubtitle -> Res.string.activity_subtitle
    DongiString.RecentUpdates -> Res.string.recent_updates
    DongiString.ActivityEmptyTitle -> Res.string.activity_empty_title
    DongiString.ActivityEmptyMessage -> Res.string.activity_empty_message
    DongiString.ActivityAddedExpense -> Res.string.activity_added_expense
    DongiString.ActivityMetadata -> Res.string.activity_metadata
    DongiString.SettingsTitle -> Res.string.settings_title
    DongiString.SettingsSubtitle -> Res.string.settings_subtitle
    DongiString.Notifications -> Res.string.notifications
    DongiString.NotificationsSubtitle -> Res.string.notifications_subtitle
    DongiString.AccountPreferences -> Res.string.account_preferences
    DongiString.DefaultCurrency -> Res.string.default_currency
    DongiString.CurrencyUsDollar -> Res.string.currency_us_dollar
    DongiString.SplitMethod -> Res.string.split_method
    DongiString.EqualSplit -> Res.string.equal_split
    DongiString.PaymentReminder -> Res.string.payment_reminder
    DongiString.EveryFriday -> Res.string.every_friday
}
