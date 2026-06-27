package com.costsplit.feature.expenses.data.remote.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

@Serializable
data class CreateUserRequest(
    val displayName: String,
    val email: String,
)

@Serializable
data class UserResponse(
    val id: String,
    val displayName: String,
    val email: String,
    val createdAt: String,
)

@Serializable
data class CreateGroupRequest(
    val name: String,
    val ownerUserId: String,
    val memberUserIds: Set<String> = emptySet(),
)

@Serializable
data class AddGroupMemberRequest(
    val userId: String,
)

@Serializable
data class GroupMemberResponse(
    val userId: String,
    val displayName: String,
    val email: String,
    val joinedAt: String,
)

@Serializable
data class GroupResponse(
    val id: String,
    val name: String,
    val ownerUserId: String,
    val members: List<GroupMemberResponse>,
    val createdAt: String,
)

@Serializable
data class CreateExpenseRequest(
    val description: String,
    @Serializable(with = DecimalStringSerializer::class)
    val totalAmount: String,
    val currency: String,
    val paidByUserId: String,
    val participantUserIds: Set<String>,
)

@Serializable
data class ExpenseShareResponse(
    val userId: String,
    val displayName: String,
    @Serializable(with = DecimalStringSerializer::class)
    val amountOwed: String,
)

@Serializable
data class ExpenseResponse(
    val id: String,
    val groupId: String,
    val description: String,
    @Serializable(with = DecimalStringSerializer::class)
    val totalAmount: String,
    val currency: String,
    val paidByUserId: String,
    val paidByDisplayName: String,
    val splitType: String,
    val shares: List<ExpenseShareResponse>,
    val createdAt: String,
)

@Serializable
data class MemberBalanceResponse(
    val userId: String,
    val displayName: String,
    @Serializable(with = DecimalStringSerializer::class)
    val netAmount: String,
)

@Serializable
data class SuggestedSettlementResponse(
    val fromUserId: String,
    val toUserId: String,
    @Serializable(with = DecimalStringSerializer::class)
    val amount: String,
)

@Serializable
data class CurrencyBalanceResponse(
    val currency: String,
    val members: List<MemberBalanceResponse>,
    val suggestedSettlements: List<SuggestedSettlementResponse>,
)

@Serializable
data class GroupBalancesResponse(
    val groupId: String,
    val balances: List<CurrencyBalanceResponse>,
)

object DecimalStringSerializer : KSerializer<String> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("DecimalString", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): String {
        val jsonDecoder = decoder as? JsonDecoder
            ?: return decoder.decodeString()
        val primitive = jsonDecoder.decodeJsonElement() as? JsonPrimitive
        return primitive?.contentOrNull ?: primitive.toString()
    }

    override fun serialize(encoder: Encoder, value: String) {
        encoder.encodeString(value)
    }
}
