//package com.example.gateway.api.controller;
//
//import com.example.gateway.api.exception.ExceptionCode;
//import com.example.gateway.api.exception.GatewayApiException;
//import com.example.gateway.api.service.JTradeLogService;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//@RestController
//@RequestMapping(value = "/api/v1/tradelog", produces = "application/json")
//public class JTradeLogController {
//
//    private JTradeLogService tradeLogService;
//
//    public JTradeLogController(JTradeLogService tradeLogService) {
//        this.tradeLogService = tradeLogService;
//    }
//
//    @RequestMapping(value = "/shares/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    public void updateShareTrade(
//            @RequestHeader(name = "accountId") String accountId,
//            @PathVariable(name = "id") String transactionId,
//            @RequestBody ShareJournalGWModel model) {
//        tradeLogService.updateShareTrade(accountId, transactionId, model);
//    }
//
//    @RequestMapping(value = "/options/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteOptionTrade(
//            @RequestHeader(name = "accountId") String accountId,
//            @PathVariable(name = "id") String transactionId) {
//        tradeLogService.deleteOptionTrade(accountId, transactionId);
//    }
//
//
//    @RequestMapping(value = "/dividends", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.OK)
//    public DividendGWModel createDividendRecord(
//            @RequestHeader(name = "accountId") String accountId,
//            @RequestBody DividendGWModel model) {
//        return tradeLogService.createDividendRecord(accountId, model);
//    }
//
//
//    @RequestMapping(value = "/dividends/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteDividendRecord(
//            @RequestHeader(name = "accountId") String accountId,
//            @PathVariable(name = "id") String transactionId) {
//        tradeLogService.deleteDividendRecord(accountId, transactionId);
//    }
//}
