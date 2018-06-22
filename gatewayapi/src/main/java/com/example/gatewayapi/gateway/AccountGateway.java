package com.example.gatewayapi.gateway;

import com.example.account.model.AccountModel;
import com.example.common.rest.envelope.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Optional;


@Component
public class AccountGateway extends AbstractGateway {

    private static final Logger log = LoggerFactory.getLogger(AccountGateway.class);

    @Value("${gateway.account.address}")
    private String SERVICE_URL;

    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }

    public Optional<AccountModel> getAccount(String tenantId, BigInteger accountId) {
        String path = SERVICE_URL + "/api/v1/" + tenantId + "/" + accountId;
        ResponseEntity<ResponseEnvelope<AccountModel>> response = restTemplate.exchange(path, HttpMethod.GET, null, new ParameterizedTypeReference<ResponseEnvelope<AccountModel>>() {});

        if (response.getStatusCode() != HttpStatus.OK) {
            return Optional.empty();
        }

        ResponseEnvelope<AccountModel> envelope = response.getBody();

        if (envelope.getErrors() != null) {
            processErrors(envelope.getErrors());
        }

        return Optional.of(envelope.getData());
    }



//    public Optional<AccountModel> getAccount(String tenantId, String email, String password) {
//        String path = SERVICE_URL + "/api/v1/" + tenantId;
//        Response response =
//                target.path("/" + tenantId)
//                        .queryParam("email", email)
//                        .queryParam("password", password)
//                        .request(MediaType.APPLICATION_JSON)
//                        .get(Response.class);
//
//        return getModel(response);
//    }

}
