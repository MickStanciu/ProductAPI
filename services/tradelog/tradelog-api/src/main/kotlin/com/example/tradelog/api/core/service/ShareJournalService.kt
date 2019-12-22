package com.example.tradelog.api.core.service

import com.example.tradelog.api.core.converter.TradeSummaryUtil
import com.example.tradelog.api.core.model.TradeSummaryModel
import com.example.tradelog.api.db.repository.ShareJournalRepository
import org.springframework.stereotype.Service

@Service
class ShareJournalService(private val repository: ShareJournalRepository) {

    fun getSummaries(accountId: String): Map<String, TradeSummaryModel> {
        val modelList = repository.getSummaries(accountId)
        return TradeSummaryUtil.toMap(models = modelList)
    }
}

