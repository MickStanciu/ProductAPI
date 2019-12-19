package com.example.tradelog.api.core.service

import com.example.tradelog.api.core.model.TradeSummaryModel
import org.springframework.stereotype.Service

@Service
class DividendJournalService {
    fun getSummaries(accountId: String): Map<String, TradeSummaryModel> {
        return emptyMap()
    }
}