package com.example.accountapi.repository;

import com.example.accountapi.model.AccountModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AccountRepository {

    private static final Logger log = LoggerFactory.getLogger(AccountRepository.class);

    private static final String ACCOUNT_READ_FIRST_QUERY = "SELECT a.id FROM accounts a " +
            "INNER JOIN account_roles ar ON a.role_fk = ar.id AND CAST(ar.tenant_fk AS uuid) = CAST(a.tenant_fk AS uuid) " +
            "LIMIT 1";

    private static final String ACCOUNT_READ_BY_EMAIL_AND_PASSWORD_QUERY = "SELECT a.id AS account_id, a.name AS account_name, " +
            "a.password, a.email, CAST(a.tenant_fk AS VARCHAR(36)) AS tenant_id, a.active, a.role_fk " +
            "FROM accounts a " +
            "WHERE a.email = ? AND a.password = ? AND a.tenant_fk = CAST(? AS uuid)";

    private static final String ACCOUNT_READ_BY_ID_QUERY = "SELECT a.id AS account_id, a.name AS account_name, a.password, " +
            "a.email, CAST(a.tenant_fk AS VARCHAR(36)) AS tenant_id, a.active, a.role_fk " +
            "FROM accounts a " +
            "WHERE a.tenant_fk = CAST(? AS uuid) AND a.id = ?";

    private static final String ACCOUNT_CREATE_QUERY = "INSERT INTO accounts (tenant_fk, role_fk, name, email, password, active) " +
            "VALUES (CAST(? AS uuid), ?, ?, ?, ?, false)";

    private static final String ACCOUNT_CHECK_QUERY = "SELECT id FROM accounts " +
            "WHERE email = ? AND tenant_fk = CAST(? AS uuid)";

    private static final String ACCOUNT_UPDATE_QUERY = "UPDATE accounts SET " +
            "name = ?, password = ?, email = ?, active = ?, role_fk = ? " +
            "WHERE tenant_fk = CAST(? AS uuid) AND id = ?";


    private static final String ACCOUNTS_READ_BY_RELATIONSHIP = "SELECT a.id AS account_id, a.name AS account_name, " +
            "? as password, a.email, CAST(a.tenant_fk AS VARCHAR(36)) AS tenant_id, a.active, a.role_fk " +
            "FROM account_relationships ar " +
            "INNER JOIN accounts a ON ar.child_fk = a.id AND CAST(ar.tenant_fk AS uuid) = CAST(a.tenant_fk AS uuid) " +
            "WHERE " +
            "ar.tenant_fk = CAST(? AS uuid) " +
            "and ar.parent_fk = ? " +
            "and ar.depth <= ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean checkFirstRecord() {
        List<AccountModel> results = jdbcTemplate.query(ACCOUNT_READ_FIRST_QUERY, new AccountModelRowMapper());
        return results.size() == 1;
    }

    public AccountModel getAccount(String tenantId, String email, String password) {
        Object[] parameters = new Object[] {email, password, tenantId};

        List<AccountModel> results = jdbcTemplate.query(ACCOUNT_READ_BY_EMAIL_AND_PASSWORD_QUERY, parameters, new AccountModelRowMapper());

        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    public AccountModel getAccount(String tenantId, long accountId) {
        Object[] parameters = new Object[] {tenantId, accountId};

        List<AccountModel> results = jdbcTemplate.query(ACCOUNT_READ_BY_ID_QUERY, parameters, new AccountModelRowMapper());

        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    public List<AccountModel> getAccountsByRelationship(String tenantId, long parentId, int depth) {
        Object[] parameters = new Object[] {"*****", tenantId, parentId, depth};

        return jdbcTemplate.query(ACCOUNTS_READ_BY_RELATIONSHIP, parameters, new AccountModelRowMapper());
    }

    public boolean checkAccount(String tenantId, String email) {
        Object[] parameters = new Object[] {email, tenantId};
        return jdbcTemplate.query(ACCOUNT_CHECK_QUERY, parameters, new AccountModelRowMapper()).size() != 0;
    }

    public void createAccount(String tenantId, String name, String password, String email, int roleId) {
        Object[] parameters = new Object[] {tenantId, roleId, name, email, password};
        jdbcTemplate.update(ACCOUNT_CREATE_QUERY, parameters);
    }

    public void updateAccount(String tenantId, long accountId, AccountModel newAccount) {
        Object[] parameters = new Object[] {newAccount.getName(), newAccount.getPassword(), newAccount.getEmail(), newAccount.isActive(),
                newAccount.getRole().getId(), tenantId, accountId};
        jdbcTemplate.update(ACCOUNT_UPDATE_QUERY, parameters);
    }
}

class AccountModelRowMapper implements RowMapper<AccountModel> {

    @Override
    public AccountModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return AccountModel.builder()
                .havingPersonalDetails()
                    .withTenantId(rs.getString("tenant_id"))
                    .withId(rs.getLong("account_id"))
                    .withName(rs.getString("account_name"))
                    .withEmail(rs.getString("email"))
                    .withPassword(rs.getString("password"))
                    .withFlagActive(rs.getBoolean("active"))
                .havingRole()
                    .withRoleId(rs.getInt("role_fk"))
                .build();
    }
}
