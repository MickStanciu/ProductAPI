package com.example.tradelog.api.rest

import com.example.tradelog.api.spec.model.TLDividendJournalDto
import com.example.tradelog.api.spec.model.TLDividendTransactionsResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

/*
    Note: the problem with this interface: depends on spring, but makes controller more pretty
 */
interface DividendJournalRestInterface {
    companion object {
        private const val ACCOUNT_ID_HEADER_NAME = "x-account-id"
        private const val PORTFOLIO_ID_PARAM_NAME = "portfolio-id"
    }

    @RequestMapping(value = ["/{symbol}", "/{symbol}/"], method = [RequestMethod.GET])
    @ResponseStatus(HttpStatus.OK)
    fun getAllBySymbol(
            @RequestHeader(ACCOUNT_ID_HEADER_NAME) accountId: UUID,
            @RequestHeader(PORTFOLIO_ID_PARAM_NAME) portfolioId: UUID,
            @PathVariable("symbol") symbol: String
    ): TLDividendTransactionsResponse

    @RequestMapping(value = ["", "/"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.OK)
    fun createRecord(
            @RequestHeader(ACCOUNT_ID_HEADER_NAME) accountId: UUID,
            @RequestHeader(PORTFOLIO_ID_PARAM_NAME) portfolioId: UUID,
            @RequestBody dto: TLDividendJournalDto): TLDividendJournalDto

    @RequestMapping(value = ["/{transactionId}", "/{transactionId}/"], method = [RequestMethod.PUT])
    @ResponseStatus(HttpStatus.OK)
    fun editRecord(
            @RequestHeader(ACCOUNT_ID_HEADER_NAME) accountId: UUID,
            @RequestHeader(PORTFOLIO_ID_PARAM_NAME) portfolioId: UUID,
            @PathVariable("transactionId") transactionId: UUID,
            @RequestBody dto: TLDividendJournalDto)

    @RequestMapping(value = ["/{transactionId}", "/{transactionId}/"], method = [RequestMethod.DELETE])
    @ResponseStatus(HttpStatus.OK)
    fun deleteRecord(
            @RequestHeader(ACCOUNT_ID_HEADER_NAME) accountId: UUID,
            @RequestHeader(PORTFOLIO_ID_PARAM_NAME) portfolioId: UUID,
            @PathVariable("transactionId") transactionId: UUID)
}