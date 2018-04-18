package com.example.gatewayapi.gateway.accountApi;

import com.example.common.rest.dto.ErrorDto;
import com.example.gatewayapi.gateway.ResponseEnvelope;
import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ejb.Stateless;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Stateless
public class AccountGateway {

    private static final Logger log = Logger.getLogger(AccountGateway.class);
    private static final String SERVICE_URL = "http://accountapi:8080/api";
    private AccountInterface proxy;

    public AccountGateway() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath(SERVICE_URL));
        proxy = target.proxy(AccountInterface.class);
    }

    public Optional<Account> getAccount(String tenantId, String email, String password) {
        Response response = proxy.accountByEmailAndPassword(tenantId, email, password);
        ResponseEnvelope<Account> envelope = response.readEntity(new GenericType<ResponseEnvelope<Account>>(){});
        response.close();

        if (response.getStatus() != 200 && envelope.getErrors() != null) {
            processErrors(envelope.getErrors());
        }

        if (response.getStatus() == 200 && envelope.getData() != null) {
            return Optional.of(envelope.getData());
        }
        return Optional.empty();
    }

    public Optional<Account> getAccount(String tenantId, BigInteger accountId) {
        Response response = proxy.accountById(tenantId, accountId);
        ResponseEnvelope<Account> envelope = response.readEntity(new GenericType<ResponseEnvelope<Account>>(){});
        response.close();

        if (response.getStatus() != 200 && envelope.getErrors() != null) {
            processErrors(envelope.getErrors());
        }

        if (response.getStatus() == 200 && envelope.getData() != null) {
            return Optional.of(envelope.getData());
        }
        return Optional.empty();
    }

    private void processErrors(List<ErrorDto> errors) {
        for (ErrorDto error : errors) {
            log.error(error);
        }
    }
}
