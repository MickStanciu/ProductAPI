package com.example.gateway.api.core.model

import java.time.OffsetDateTime

class OptionJournalModel(val transactionId: String,
                         val stockSymbol: String,
                         val stockPrice: Double,
                         val strikePrice: Double,
                         val contracts: Int,
                         val premium: Double,
                         val expiryDate: OffsetDateTime,
                         val date: OffsetDateTime,
                         val brokerFees: Double,
                         val groupSelected: Boolean,
                         val legClosed: Boolean,
                         val transactionType: TransactionType,
                         val action: ActionType)
