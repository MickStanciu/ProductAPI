package com.example.tradelog.api.core.service

import com.example.tradelog.api.core.converter.TradeSummaryUtil
import com.example.tradelog.api.core.model.OptionJournalModel
import com.example.tradelog.api.core.model.TradeSummaryModel
import com.example.tradelog.api.db.repository.OptionJournalRepository
import org.springframework.stereotype.Service

@Service
class OptionJournalService(private val repository: OptionJournalRepository) : JournalService<OptionJournalModel> {

    override fun getSummaries(accountId: String): Map<String, TradeSummaryModel> {
        val modelList = repository.getSummaries(accountId)
        return TradeSummaryUtil.toMap(models = modelList)
    }

    override fun getAllBySymbol(accountId: String, portfolioId: String, symbol: String): List<OptionJournalModel> {
        return repository.getAllBySymbol(accountId, portfolioId, symbol)
    }

    override fun createRecord(transactionId: String, model: OptionJournalModel): OptionJournalModel? {
        repository.createRecord(transactionId, model)
        return repository.getById(model.transactionDetails.accountId, transactionId)
    }

    override fun editRecord(model: OptionJournalModel): Boolean {
        return repository.editRecord(model)
    }

    override fun deleteRecord(transactionId: String): Boolean {
        return repository.deleteRecord(transactionId)
    }

    override fun getById(accountId: String, transactionId: String): OptionJournalModel? {
        return repository.getById(accountId, transactionId)
    }
}
