package com.example.tradelog.api.rest.controller

import com.example.tradelog.api.core.facade.JournalFacade
import com.example.tradelog.api.rest.converter.DividendJournalModelConverter
import com.example.tradelog.api.rest.exception.ExceptionCode
import com.example.tradelog.api.rest.exception.TradeLogException
import com.example.tradelog.api.rest.validator.RequestValidator
import com.example.tradelog.api.spec.model.DividendJournalDto
import com.example.tradelog.api.spec.model.DividendTransactionsResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping(value = ["/api/v1/dividends"], produces = [DividendJournalController.PROTOBUF_MEDIA_TYPE_VALUE, MediaType.APPLICATION_JSON_VALUE])

class DividendJournalController(private val journalFacade: JournalFacade) {

    companion object {
        const val PROTOBUF_MEDIA_TYPE_VALUE = "application/x-protobuf"
        private val LOG = LoggerFactory.getLogger(DividendJournalController::class.java)
    }

    @RequestMapping(value = ["/{symbol}", "/{symbol}/"], method = [RequestMethod.GET])
    @ResponseStatus(HttpStatus.OK)
    fun getAllBySymbol(
            @RequestHeader("accountId") accountId: String,
            @PathVariable("symbol") symbol: String) : DividendTransactionsResponse {

        if (!RequestValidator.validateGetAllBySymbol(accountId, symbol)) {
            throw TradeLogException(ExceptionCode.BAD_REQUEST)
        }

        val models = journalFacade.getAllDividendTradesBySymbol(accountId, symbol)
        val dtos = models.stream()
                .map { m -> DividendJournalModelConverter.toDto(m) }
                .collect(Collectors.toList())

        return DividendTransactionsResponse.newBuilder()
                .addAllDividendItems(dtos)
                .build()
    }


    @RequestMapping(value = ["", "/"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.OK)
    fun createRecord(
            @RequestHeader("accountId") accountId: String,
            @RequestBody dto: DividendJournalDto): DividendJournalDto {

        if (!RequestValidator.validateCreateDividendRecord(accountId, dto)) {
            throw TradeLogException(ExceptionCode.BAD_REQUEST)
        }

        val model = journalFacade.createDividendRecord(DividendJournalModelConverter.toModel(dto))

        if (model == null) {
            LOG.error("Could not create for: ${dto.transactionDetails.symbol}")
            throw TradeLogException(ExceptionCode.CREATE_DIVIDEND_FAILED)
        }

        return DividendJournalModelConverter.toDto(model)
    }


    @RequestMapping(value = ["/{transactionId}", "/{transactionId}/"], method = [RequestMethod.PUT])
    @ResponseStatus(HttpStatus.OK)
    fun editRecord(
            @RequestHeader("accountId") accountId: String,
            @PathVariable("transactionId") transactionId: String,
            @RequestBody dto: DividendJournalDto) {

        if (!RequestValidator.validateEditDividendRecord(accountId, transactionId, dto)) {
            throw TradeLogException(ExceptionCode.BAD_REQUEST)
        }

        if (!journalFacade.editDividendRecord(transactionId, DividendJournalModelConverter.toModel(dto))) {
            LOG.error("Could not edit for: $transactionId")
            throw TradeLogException(ExceptionCode.EDIT_DIVIDEND_FAILED)
        }
    }


    @RequestMapping(value = ["/{transactionId}", "/{transactionId}/"], method = [RequestMethod.DELETE])
    @ResponseStatus(HttpStatus.OK)
    fun deleteRecord(@RequestHeader("accountId") accountId: String,
                     @PathVariable("transactionId") transactionId: String) {

        if (!RequestValidator.validateDeleteDividendRecord(accountId, transactionId)) {
            throw TradeLogException(ExceptionCode.BAD_REQUEST)
        }

        if (!journalFacade.deleteDividendRecord(accountId, transactionId)) {
            LOG.error("Could not delete for: $transactionId")
            throw TradeLogException(ExceptionCode.DELETE_DIVIDEND_FAILED)
        }
    }
}
