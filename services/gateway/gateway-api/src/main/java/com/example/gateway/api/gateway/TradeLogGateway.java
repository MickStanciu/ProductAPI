package com.example.gateway.api.gateway;

import com.example.tradelog.api.spec.model.JournalModel;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TradeLogGateway {

    private static final Logger log = LoggerFactory.getLogger(TradeLogGateway.class);

    private TradeLogApiProxy tradeLogApiProxy;

    @Autowired
    public TradeLogGateway(TradeLogApiProxy tradeLogApiProxy) {
        this.tradeLogApiProxy = tradeLogApiProxy;
    }

    public List<JournalModel> getAllByAccountId(String accountId) {
        try {
            ResponseEntity<List<JournalModel>> responseEntity = tradeLogApiProxy.getAllByAccountId(accountId);
            return responseEntity.getBody();
        } catch (FeignException fex) {
            log.error(fex.getMessage());
            return Collections.emptyList();
        }
    }

    private List<JournalModel> getFake() {
        return Collections.emptyList();
    }
}
