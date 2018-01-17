package com.example.account.validation;

import com.example.account.model.Account;
import com.example.account.model.Role;
import org.junit.Test;

import java.math.BigInteger;

import static com.example.account.validation.RequestValidation.validateCreateAccount;
import static com.example.account.validation.RequestValidation.validateGetAccount;
import static com.example.account.validation.RequestValidation.validateUpdateAccount;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequestValidationTest {

    private static String DEFAULT_TENANT_ID = "d79ec11a-2011-4423-ba01-3af8de0a3e10";
    private static BigInteger DEFAULT_ACCOUNT_ID = BigInteger.ONE;
    private static String DEFAULT_ACCOUNT_NAME = "name.surname";
    private static String DEFAULT_ACCOUNT_PASSWORD = "secret";
    private static String DEFAULT_ACCOUNT_EMAIL = "not.set@domain.com";

    @Test
    public void testValidateGetAccountWhenValid() {
        boolean response = validateGetAccount(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_NAME, DEFAULT_ACCOUNT_PASSWORD);
        assertTrue("Should be TRUE", response);
    }


    @Test
    public void testValidateGetAccountWhenTenantIdIsInvalid() {
        boolean response = validateGetAccount(null, DEFAULT_ACCOUNT_NAME, DEFAULT_ACCOUNT_PASSWORD);
        assertFalse("Should be FALSE", response);

        response = validateGetAccount("!23", DEFAULT_ACCOUNT_NAME, DEFAULT_ACCOUNT_PASSWORD);
        assertFalse("Should be FALSE", response);
    }


    @Test
    public void testValidateGetAccountWhenAccountNameIsInvalid() {
        boolean response = validateGetAccount(DEFAULT_TENANT_ID, null, DEFAULT_ACCOUNT_PASSWORD);
        assertFalse("Should be FALSE", response);

        response = validateGetAccount(DEFAULT_TENANT_ID, "", DEFAULT_ACCOUNT_PASSWORD);
        assertFalse("Should be FALSE", response);
    }


    @Test
    public void testValidateGetAccountWhenAccountPasswordIsInvalid() {
        boolean response = validateGetAccount(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_NAME, null);
        assertFalse("Should be FALSE", response);

        response = validateGetAccount(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_NAME, "");
        assertFalse("Should be FALSE", response);

        response = validateGetAccount(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_NAME, "123");
        assertFalse("Should be FALSE", response);
    }


    @Test
    public void testValidateCreateAccountWhenValid() {
        Role roleFixture = new Role(5, "L5", "");
        Account accountFixture = new Account(null, null, roleFixture, DEFAULT_ACCOUNT_NAME,
                DEFAULT_ACCOUNT_EMAIL, DEFAULT_ACCOUNT_PASSWORD, null);
        boolean response = validateCreateAccount(DEFAULT_TENANT_ID, accountFixture);
        assertTrue("Should be TRUE", response);
    }


    @Test
    public void testValidateCreateAccountWhenAccountIsInvalid() {
        Role roleFixture = new Role(5, "L5", "");

        //name
        Account accountFixture = new Account(DEFAULT_TENANT_ID, null, roleFixture, null,
                DEFAULT_ACCOUNT_EMAIL, DEFAULT_ACCOUNT_PASSWORD, true);
        boolean response = validateCreateAccount(DEFAULT_TENANT_ID, accountFixture);
        assertFalse("Should be FALSE", response);

        //email
        accountFixture = new Account(DEFAULT_TENANT_ID, null, roleFixture, DEFAULT_ACCOUNT_NAME,
                null, DEFAULT_ACCOUNT_PASSWORD, true);
        response = validateCreateAccount(DEFAULT_TENANT_ID, accountFixture);
        assertTrue("Should be TRUE", response);

        //password
        accountFixture = new Account(DEFAULT_TENANT_ID, null, roleFixture, DEFAULT_ACCOUNT_NAME,
                DEFAULT_ACCOUNT_EMAIL, null, true);
        response = validateCreateAccount(DEFAULT_TENANT_ID, accountFixture);
        assertFalse("Should be FALSE", response);
    }


    @Test
    public void testValidateUpdateAccountWhenValid() {
        Role roleFixture = new Role(5, "L5", "");
        Account accountFixture = new Account(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_ID, roleFixture, DEFAULT_ACCOUNT_NAME,
                DEFAULT_ACCOUNT_EMAIL, DEFAULT_ACCOUNT_PASSWORD, true);
        boolean response = validateUpdateAccount(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_ID, accountFixture);
        assertTrue("Should be TRUE", response);
    }


    @Test
    public void testValidateUpdateAccountWhenTenantIsInvalid() {
        Role roleFixture = new Role(5, "L5", "");
        Account accountFixture = new Account(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_ID, roleFixture, DEFAULT_ACCOUNT_NAME,
                DEFAULT_ACCOUNT_EMAIL, DEFAULT_ACCOUNT_PASSWORD, true);

        boolean response = validateUpdateAccount(null, DEFAULT_ACCOUNT_ID, accountFixture);
        assertFalse("Should be FALSE", response);
    }


    @Test
    public void testValidateUpdateAccountWhenAccountIdIsInvalid() {
        Role roleFixture = new Role(5, "L5", "");
        Account accountFixture = new Account(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_ID, roleFixture, DEFAULT_ACCOUNT_NAME,
                DEFAULT_ACCOUNT_EMAIL, DEFAULT_ACCOUNT_PASSWORD, true);

        boolean response = validateUpdateAccount(DEFAULT_TENANT_ID, null, accountFixture);
        assertFalse("Should be FALSE", response);
    }


    @Test
    public void testValidateUpdateAccountWhenAccountIsInvalid() {
        Role roleFixture = new Role(5, "L5", "");

        //name
        Account accountFixture = new Account(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_ID, roleFixture, "a",
                DEFAULT_ACCOUNT_EMAIL, DEFAULT_ACCOUNT_PASSWORD, true);
        boolean response = validateUpdateAccount(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_ID, accountFixture);
        assertFalse("Should be FALSE", response);

        //email
        accountFixture = new Account(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_ID, roleFixture, DEFAULT_ACCOUNT_NAME,
                "1", DEFAULT_ACCOUNT_PASSWORD, true);
        response = validateUpdateAccount(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_ID, accountFixture);
        assertFalse("Should be FALSE", response);

        //password
        accountFixture = new Account(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_ID, roleFixture, DEFAULT_ACCOUNT_NAME,
                DEFAULT_ACCOUNT_EMAIL, "123", true);
        response = validateUpdateAccount(DEFAULT_TENANT_ID, DEFAULT_ACCOUNT_ID, accountFixture);
        assertFalse("Should be FALSE", response);
    }

}