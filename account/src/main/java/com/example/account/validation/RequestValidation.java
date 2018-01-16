package com.example.account.validation;

import com.example.account.model.Account;
import com.example.account.model.Role;
import com.example.common.validator.IntegerValidator;
import com.example.common.validator.StringValidator;

import java.math.BigInteger;

public class RequestValidation {

    private static boolean tenantId(String tenantId) {
        return new StringValidator(tenantId)
                .notNull()
                .sizeEqualTo(36)
                .isValid();
    }

    private static boolean accountId(BigInteger accountId) {
        if (accountId == null) {
            return false;
        }

        Integer accountIdInt = accountId.intValue();
        return new IntegerValidator(accountIdInt)
                .notNull()
                .greaterThanZero()
                .isValid();
    }



    private static boolean accountName(String name) {
        return new StringValidator(name)
                .notNull()
                .sizeLessOrEqualTo(64)
                .sizeGreaterOrEqualTo(5)
                .isValid();
    }

    private static boolean accountPassword(String password) {
        return new StringValidator(password)
                .notNull()
                .sizeLessOrEqualTo(64)
                .sizeGreaterOrEqualTo(5)
                .isValid();
    }

    private static boolean accountEmail(String email) {
        return new StringValidator(email)
                .notNull()
                .sizeLessOrEqualTo(64)
                .isValid();
    }

    private static boolean role(Role role) {
        return role != null && role.getId() != null && role.getId() >= 1 && role.getId() <= 5;
    }

    private static boolean accountActive(Boolean active) {
        return active != null;
    }

    private static boolean account(Account account) {
        return account != null;
    }

    public static boolean validateUpdateAccount(String tenantId, BigInteger accountId, Account account) {
        boolean response =  RequestValidation.tenantId(tenantId) && RequestValidation.accountId(accountId)
                && RequestValidation.account(account);

        //validate optional fields
        if (account.getName() != null) {
            response = response && RequestValidation.accountName(account.getName());
        }

        if (account.getPassword() != null) {
            response = response && RequestValidation.accountPassword(account.getPassword());
        }

        if (account.getEmail() != null) {
            response = response && RequestValidation.accountEmail(account.getEmail());
        }

//                && RequestValidation.accountActive(account.isActive())
//                && RequestValidation.role(account.getRole());

        return response;
    }

    public static boolean validateCreateAccount(String tenantId, Account account) {
        return RequestValidation.tenantId(tenantId) && RequestValidation.account(account)
                && RequestValidation.accountName(account.getName())
                && RequestValidation.accountPassword(account.getPassword());
    }

    public static Boolean validateGetAccount(String tenantId, String name, String password) {
        return RequestValidation.tenantId(tenantId) && RequestValidation.accountName(name)
                && RequestValidation.accountPassword(password);
    }

}
