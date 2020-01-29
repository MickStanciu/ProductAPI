package com.example.gateway.api.rest.gateway

import com.example.gateway.api.core.model.SharePriceModel
import com.example.gateway.api.rest.converter.LastUpdatePriceResponseConverter
import com.example.gateway.api.rest.converter.ShareDataConverter
import com.example.stockdata.api.spec.model.SDLastUpdatePriceResponse
import com.example.stockdata.api.spec.model.SDPriceItemResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@Service
class StockDataGateway(
        private val restTemplate: RestTemplate,
        @Value("\${gateway.stockdata.url}") private val url: String) {

    companion object {
        private val LOG = LoggerFactory.getLogger(StockDataGateway::class.java)
        private const val PROTOBUF_MEDIA_TYPE_VALUE = "application/x-protobuf"
    }

    fun getPrice(accountId: String, symbol: String): SharePriceModel? {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/price/last-close/{symbol}")

        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)
        headers.set("accountId", accountId)

        val responseEntity = restTemplate
                .exchange(builder.build(symbol).toString(), HttpMethod.GET, HttpEntity<Any>(headers), SDPriceItemResponse::class.java)

        val responseDto: SDPriceItemResponse? = responseEntity.body

        return if (responseDto != null) {
            ShareDataConverter.toModel(responseDto)
        } else {
            null
        }
    }

    fun getSymbolsForUpdate(): List<String> {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/price/old")
                .queryParam("limit", 10)

        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)

        val responseEntity = restTemplate
                .exchange(builder.build("").toString(), HttpMethod.GET, HttpEntity<Any>(headers), SDLastUpdatePriceResponse::class.java)

        val responseDto: SDLastUpdatePriceResponse? = responseEntity.body

        return if (responseDto != null) {
            return LastUpdatePriceResponseConverter.toModel(responseDto)
        } else {
            Collections.emptyList()
        }
    }
}