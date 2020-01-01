package com.example.tradelog.api.rest.exception

import com.example.common.converter.TimeConverter
import com.example.tradelog.api.rest.converter.toExceptionCode
import com.example.tradelog.api.spec.model.ExceptionResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class CustomisedExceptionHandler: ResponseEntityExceptionHandler() {

    companion object {
        private val LOG = LoggerFactory.getLogger(CustomisedExceptionHandler::class.java)
    }

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse.newBuilder()
                .setCode(ExceptionResponse.ExceptionCode.UNKNOWN)
                .setMessage(ex.message)
                .setDetails(request.getDescription(false))
                .setTimestamp(TimeConverter.getOffsetDateTimeNow().toString())
                .build()
        LOG.error(ex.message, ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse)
    }


    @ExceptionHandler(TradeLogException::class)
    fun handleTradeLogExceptions(ex: TradeLogException, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse.newBuilder()
                .setCode(toExceptionCode(ex.code))
                .setMessage(ex.message)
                .setDetails(request.getDescription(false))
                .setTimestamp(TimeConverter.getOffsetDateTimeNow().toString())
                .build()
        LOG.error(ex.message, ex)
        return when (ex.code) {
            ExceptionCode.BAD_REQUEST -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse)
            ExceptionCode.DELETE_SHARE_FAILED -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse)
            ExceptionCode.EDIT_SHARE_FAILED -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse)
            else -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse)
            //TODO: consider TRADELOG_EMPTY, DELETE_X_FAILED
        }
    }


}