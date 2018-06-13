package com.example.account.controller;

import com.example.account.exception.ExceptionCode;
import com.example.account.facade.AccountFacade;
import com.example.account.model.AccountModel;
import com.example.common.rest.dto.ErrorDto;
import com.example.common.rest.envelope.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Path("/api/v1")
@Produces("application/json")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private AccountFacade accountFacade;

    @Autowired
    public AccountController(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    @GET
    @Path("/{tenantId}")
    public ResponseEntity<?> accountByEmailAndPassword(
            @PathParam("tenantId") @DefaultValue("0") String tenantId,
            @QueryParam("email") @DefaultValue("") String email,
            @QueryParam("password") @DefaultValue("") String password
    ) {
        if (!RequestValidation.validateGetAccount(tenantId, email, password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //todo: catch all errors
        Optional<AccountModel> accountOptional;
        try {
            accountOptional = accountFacade.getAccount(tenantId, email, password);
        } catch (Exception ex) {
            log.error("", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        List<ErrorDto> errors = new ArrayList<>();
        AccountModel account = null;
        if (!accountOptional.isPresent()) {
            errors.add(new ErrorDto(ExceptionCode.ACCOUNT_NOT_FOUND.name(), ExceptionCode.ACCOUNT_NOT_FOUND.getMessage()));
        } else {
            account = accountOptional.get();
        }

        ResponseEnvelope responseEnvelope = new ResponseEnvelope.Builder<AccountModel>()
                .withData(account)
                .withErrors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseEnvelope);
    }

    @GET
    @Path("/{tenantId}/{accountId}")
    public ResponseEntity<?> accountById(
            @PathParam("tenantId") @DefaultValue("0") String tenantId,
            @PathParam("accountId") @DefaultValue("0") BigInteger accountId
    ) {
        if (!RequestValidation.validateGetAccount(tenantId, accountId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GET
    @Path("/relations/{tenantId}/{parentId}")
    public ResponseEntity<?> getAccountByRelationship(
            @PathParam("tenantId") @DefaultValue("0") String tenantId,
            @PathParam("parentId") @DefaultValue("0") BigInteger parentId,
            @QueryParam("depth") Integer depth
    ) {
        if (!RequestValidation.validateGetAccountsByRelationship(tenantId, parentId, depth)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @POST
    @Path("/{tenantId}")
    @Consumes("application/json")
    public ResponseEntity<?> createAccount(
            AccountModel account,
            @PathVariable(name = "tenantId") String tenantId
    ) {
        if (!RequestValidation.validateCreateAccount(tenantId, account)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PUT
    @Path("/{tenantId}/{accountId}")
    @Consumes("application/json")
    public ResponseEntity<?> updateAccount(
            AccountModel account,
            @PathVariable(name = "tenantId") String tenantId,
            @PathVariable(name = "accountId") BigInteger accountId
    ) {
        if (!RequestValidation.validateUpdateAccount(tenantId, accountId, account)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
