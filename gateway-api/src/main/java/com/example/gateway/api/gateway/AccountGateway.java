package com.example.gateway.api.gateway;

import com.example.account.api.spec.model.AccountModel;
import com.example.account.api.spec.model.RoleInfoModel;
import com.example.account.api.spec.model.RoleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountGateway {

    private static final Logger log = LoggerFactory.getLogger(AccountGateway.class);

    @Value("gateway.account.address")
    private String SERVICE_URL = "localhost";

    @PostConstruct
    public void init() {
        log.debug(AccountGateway.class.getName() + " was initialized");
    }

    public Optional<AccountModel> getAccount(String tenantId, BigInteger accountId) {
        return getFake();
//
//        UriBuilder builder = UriBuilder.builder(SERVICE_URL)
//                .addPath("rest")
//                .addPath("v1")
//                .addPath(tenantId)
//                .addPath(accountId.toString());
//
//        return getAccountModel(builder.build());
    }

//    public Optional<AccountModel> getAccount(String tenantId, String email, String password) {
//        UriBuilder builder = UriBuilder.builder(SERVICE_URL)
//                .addPath("rest")
//                .addPath("v1")
//                .addPath(tenantId)
//                .addQuery("email", email)
//                .addQuery("password", password);
//
//        return getAccountModel(builder.build());
//    }


//    private Optional<AccountModel> getAccountModel(URI uri) {
//        WebTarget target = this.getTarget(uri);
//
//        ResponseEnvelope<AccountModel> envelope;
//
//        try {
//            Response response = target
//                    .request(MediaType.APPLICATION_JSON)
//                    .get(Response.class);
//            envelope = response.readEntity(new GenericType<ResponseEnvelope<AccountModel>>() {});
//            response.close();
//        }  catch (Exception ex) {
//            log.error("Hmmm", ex);
//            return Optional.empty();
//        }
//
//        if (envelope.getErrors() != null) {
//            processErrors(envelope.getErrors());
//        }
//
//        return Optional.of(envelope.getData());
//    }

    private Optional<AccountModel> getFake() {
        Set<RoleInfoModel> roles = new HashSet<>();
        roles.add(RoleInfoModel.LOG_IN);
        roles.add(RoleInfoModel.CREATE_ACCOUNT);

        AccountModel model = AccountModel.builder()
                .havingPersonalDetails()
                .withFlagActive(true)
                .withTenantId("123")
                .withEmail("test@example.com")
                .withName("Mick")
                .withPassword("123")
                .withId(1L)
                .havingRole()
                .withRoleId(1)
                .withRole(RoleModel.builder()
                        .withPermissions(roles)
                        .withId(1)
                        .withName("TEST ROLE")
                        .build())
                .build();
        return Optional.of(model);
    }


}