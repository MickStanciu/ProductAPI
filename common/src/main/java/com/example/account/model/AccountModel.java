package com.example.account.model;

import java.io.Serializable;
import java.math.BigInteger;

public class AccountModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigInteger id;
    private String name;
    private String email;
    private String password;
    private Boolean active;

    private String tenantId;
    private Integer roleId;

    private RoleModel role;

    public AccountModel() {
        //required by Jackson
    }

    public BigInteger getId() {
        return id;
    }

    public RoleModel getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTenantId() {
        return tenantId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Boolean isActive() {
        return active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(AccountModel account) {
        return new Builder(account);
    }

    public static class Builder {
        AccountModel account;

        public Builder() {
            account = new AccountModel();
        }

        public Builder(AccountModel account) {
            this.account = account;
        }

        public AccountModel build() {
            return account;
        }

        public RoleBuilder havingRole() {
            return new RoleBuilder(account);
        }

        public AccountBuilder havingPersonalDetails() {
            return new AccountBuilder(account);
        }
    }

    public static class RoleBuilder extends Builder {
        RoleBuilder(AccountModel account) {
            super(account);
        }

        public RoleBuilder withRoleId(Integer roleId) {
            account.roleId = roleId;
            return this;
        }

        public RoleBuilder withRole(RoleModel role) {
            account.role = role;
            return this;
        }
    }

    public static class PermissionBuilder extends Builder {
        public PermissionBuilder(AccountModel account) {
            super(account);
        }
    }

    public static class AccountBuilder extends Builder {
        AccountBuilder(AccountModel account) {
            this.account = account;
        }

        public AccountBuilder withId(BigInteger id) {
            account.id = id;
            return this;
        }

        public AccountBuilder withName(String name) {
            account.name = name;
            return this;
        }

        public AccountBuilder withEmail(String email) {
            account.email = email;
            return this;
        }

        public AccountBuilder withPassword(String password) {
            account.password = password;
            return this;
        }

        public AccountBuilder withTenantId(String tenantId) {
            account.tenantId = tenantId;
            return this;
        }

        public AccountBuilder withFlagActive(boolean active) {
            account.active = active;
            return this;
        }
    }

}
