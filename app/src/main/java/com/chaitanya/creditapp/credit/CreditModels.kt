package com.chaitanya.creditapp.credit

data class ApiResponse(
    val items: List<Item>
)

data class Item(
    val open_state: OpenState,
    val closed_state: ClosedState,
    val cta_text: String
)

data class OpenState(
    val body: Body
)

data class ClosedState(
    val body: ClosedBody
)

data class Body(
    val title: String,
    val subtitle: String,
    val card: Card,
    val items: List<EmiItem> ,
    val footer: String
)

data class Card(
    val header: String,
    val description: String,
    val max_range: Int,
    val min_range: Int
)

data class EmiItem(
    val emi: String,
    val duration: String,
    val title: String,
    val subtitle: String,
    val tag: String? = null
)

data class BankItem(
    val icon: String,
    val title: String,
    val subtitle: Long
)

data class ClosedBody(
    val key1: String? = null,
    val key2: String? = null
)
