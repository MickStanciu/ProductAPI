package com.example.gateway.api.rest.gateway

import com.example.gateway.api.core.model.*
import com.example.gateway.api.rest.converter.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors
import com.example.tradelog.api.spec.model.ActiveSymbolsResponse as TLActiveSymbolsResponse
import com.example.tradelog.api.spec.model.DividendTransactionsResponse as TLDividendTransactionsResponse
import com.example.tradelog.api.spec.model.OptionJournalDto as TLOptionJournalDto
import com.example.tradelog.api.spec.model.OptionTransactionsResponse as TLOptionTransactionsResponse
import com.example.tradelog.api.spec.model.ShareJournalDto as TLShareJournalDto
import com.example.tradelog.api.spec.model.ShareTransactionsResponse as TLShareTransactionsResponse
import com.example.tradelog.api.spec.model.TradeSummaryResponse as TLTradeSummaryResponse

@Service
class TradeLogGateway(private val restTemplate: RestTemplate,
                      @Value("\${gateway.tradelog.url}") private val url: String) {

    companion object {
        private val LOG = LoggerFactory.getLogger(TradeLogGateway::class.java)
        private const val PROTOBUF_MEDIA_TYPE_VALUE = "application/x-protobuf"
    }

    fun getAllTradedSymbols(accountId: String): List<String> {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/transactions/symbols")
        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)
        headers.set("accountId", accountId)

        val responseEntity = restTemplate
                .exchange(builder.build("").toString(), HttpMethod.GET, HttpEntity<Any>(headers), TLActiveSymbolsResponse::class.java)

        val dto: TLActiveSymbolsResponse? = responseEntity.body

        return if (dto != null) {
            ActiveSymbolsResponseConverter.toModel(responseEntity.body!!)
        } else {
            Collections.emptyList()
        }
    }

    @Async("asyncExecutor")
    fun getAllShareTransactions(accountId: String, symbol: String): CompletableFuture<List<ShareJournalModel>> {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/shares/{symbol}")

        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)
        headers.set("accountId", accountId)

        val responseEntity = restTemplate
                .exchange(builder.build(symbol).toString(), HttpMethod.GET, HttpEntity<Any>(headers), TLShareTransactionsResponse::class.java)

        val dto: TLShareTransactionsResponse? = responseEntity.body

        return if (dto != null) {
            CompletableFuture.completedFuture(
                    dto.shareItemsList.stream().map { ShareJournalConverter.toModel(it) }.collect(Collectors.toList())
            )
        } else {
            CompletableFuture.completedFuture(Collections.emptyList())
        }
    }

    @Async("asyncExecutor")
    fun getAllOptionTransactions(accountId: String, symbol: String): CompletableFuture<List<OptionJournalModel>> {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/options/{symbol}")

        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)
        headers.set("accountId", accountId)

        val responseEntity = restTemplate
                .exchange(builder.build(symbol).toString(), HttpMethod.GET, HttpEntity<Any>(headers), TLOptionTransactionsResponse::class.java)

        val dto: TLOptionTransactionsResponse? = responseEntity.body

        return if (dto != null) {
            CompletableFuture.completedFuture(
                    dto.optionItemsList.stream().map { OptionJournalConverter.toModel(it) }.collect(Collectors.toList())
            )
        } else {
            CompletableFuture.completedFuture(Collections.emptyList())
        }
    }

    @Async("asyncExecutor")
    fun getAllDividendTransactions(accountId: String, symbol: String): CompletableFuture<List<DividendJournalModel>> {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/dividends/{symbol}")

        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)
        headers.set("accountId", accountId)

        val responseEntity = restTemplate
                .exchange(builder.build(symbol).toString(), HttpMethod.GET, HttpEntity<Any>(headers), TLDividendTransactionsResponse::class.java)

        val dto: TLDividendTransactionsResponse? = responseEntity.body

        return if (dto != null) {
            CompletableFuture.completedFuture(
                    dto.dividendItemsList.stream().map { DividendJournalConverter.toModel(it) }.collect(Collectors.toList())
            )
        } else {
            CompletableFuture.completedFuture(Collections.emptyList())
        }
    }

    fun getSummary(accountId: String): List<TradeSummaryModel> {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/transactions/summary")

        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)
        headers.set("accountId", accountId)

        val responseEntity = restTemplate
                .exchange(builder.build("").toString(), HttpMethod.GET, HttpEntity<Any>(headers), TLTradeSummaryResponse::class.java)

        val dto: TLTradeSummaryResponse? = responseEntity.body

        return if (dto != null) {
                dto.itemsList.stream().map { TradeSummaryItemConverter.toModel(it) }.collect(Collectors.toList())
        } else {
            Collections.emptyList()
        }
    }

    fun createShareTransaction(accountId: String, model: ShareJournalModel): ShareJournalModel? {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/shares")

        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)
        headers.set("accountId", accountId)

        val requestDto = ShareJournalConverter.toTLDto(model)
        val responseEntity = restTemplate
                .exchange(builder.build("").toString(), HttpMethod.POST, HttpEntity<Any>(requestDto, headers), TLShareJournalDto::class.java)

        val responseDto: TLShareJournalDto? = responseEntity.body

        return if (responseDto != null) {
            ShareJournalConverter.toModel(responseDto)
        } else {
            null
        }
    }

    fun deleteShareTransaction(accountId: String, transactionId: String) {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/shares/{id}")

        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)
        headers.set("accountId", accountId)

        restTemplate.exchange(builder.build(transactionId).toString(), HttpMethod.DELETE, HttpEntity<Any>(headers), Any::class.java)
    }

    fun createOptionTransaction(accountId: String, model: OptionJournalModel): OptionJournalModel? {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/options")

        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)
        headers.set("accountId", accountId)

        val requestDto = OptionJournalConverter.toTLDto(model)
        val responseEntity = restTemplate
                .exchange(builder.build("").toString(), HttpMethod.POST, HttpEntity<Any>(requestDto, headers), TLOptionJournalDto::class.java)

        val responseDto: TLOptionJournalDto? = responseEntity.body

        return if (responseDto != null) {
            OptionJournalConverter.toModel(responseDto)
        } else {
            null
        }
    }

    fun deleteOptionTransaction(accountId: String, transactionId: String) {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/options/{id}")

        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)
        headers.set("accountId", accountId)

        restTemplate.exchange(builder.build(transactionId).toString(), HttpMethod.DELETE, HttpEntity<Any>(headers), Any::class.java)
    }

    fun updateTransactionSettings(accountId: String, model: TransactionSettingsModel) {
        val builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("transactions/settings/{id}")

        val headers = HttpHeaders()
        headers.set("Content-Type", PROTOBUF_MEDIA_TYPE_VALUE)
        headers.set("accountId", accountId)

        val requestDto = TransactionSettingsConverter.toDto(model)
        restTemplate.exchange(builder.build(model.transactionId).toString(), HttpMethod.POST, HttpEntity<Any>(requestDto, headers), Any::class.java)
    }
}