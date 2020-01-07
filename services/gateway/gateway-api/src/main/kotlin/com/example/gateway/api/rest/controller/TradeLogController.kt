package com.example.gateway.api.rest.controller

import com.example.gateway.api.core.service.TradeLogService
import com.example.gateway.api.rest.controller.TradeLogController.Companion.PROTOBUF_MEDIA_TYPE_VALUE
import com.example.gateway.api.rest.converter.TradeLogConverter
import com.example.gateway.api.spec.model.ActiveSymbolsResponse
import com.example.gateway.api.spec.model.TradeLogDto
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/v1/tradelog"], produces = [PROTOBUF_MEDIA_TYPE_VALUE, MediaType.APPLICATION_JSON_VALUE])
class TradeLogController(private val tradeLogService: TradeLogService) {

    companion object {
        const val PROTOBUF_MEDIA_TYPE_VALUE = "application/x-protobuf"
    }

    @RequestMapping(value = ["/symbols"], method = [RequestMethod.GET])
    @ResponseStatus(HttpStatus.OK)
    fun getAllTradedSymbols(@RequestHeader("accountId") accountId: String): ActiveSymbolsResponse {

        //todo: validate input

        val tradedSymbols: List<String> = tradeLogService.getAllTradedSymbols(accountId)

        return ActiveSymbolsResponse.newBuilder()
                .addAllSymbols(tradedSymbols)
                .build()
    }

    @RequestMapping(value = ["/all/{symbol}"], method = [RequestMethod.GET])
    @ResponseStatus(HttpStatus.OK)
    fun getAll(
            @RequestHeader("accountId") accountId: String,
            @PathVariable(name = "symbol") symbol: String): TradeLogDto {

        //todo: validate input

        val tradeLogModel = tradeLogService.getAll(accountId, symbol)

        return TradeLogConverter.toDto(tradeLogModel)
    }

}