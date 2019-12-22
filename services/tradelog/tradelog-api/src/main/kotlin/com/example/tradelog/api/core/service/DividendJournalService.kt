package com.example.tradelog.api.core.service

import com.example.tradelog.api.core.converter.TradeSummaryUtil
import com.example.tradelog.api.core.model.TradeSummaryModel
import com.example.tradelog.api.db.repository.DividendJournalRepository
import org.springframework.stereotype.Service

@Service
class DividendJournalService(private val repository: DividendJournalRepository) {

    fun getSummaries(accountId: String): Map<String, TradeSummaryModel> {
        val modelList = repository.getSummaries(accountId)
        return TradeSummaryUtil.toMap(models = modelList)
    }
}