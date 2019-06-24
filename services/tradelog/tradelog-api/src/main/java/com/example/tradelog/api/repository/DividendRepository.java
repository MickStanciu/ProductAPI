package com.example.tradelog.api.repository;

import com.example.tradelog.api.spec.model.DividendJournalModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DividendRepository {

    private static final String DIVIDEND_READ_BY_SYMBOL_FOR_ACCOUNT =
            "SELECT CAST(tl.id AS VARCHAR(36)), " +
                    "       CAST(tl.account_fk AS VARCHAR(36)), " +
                    "       tl.date, " +
                    "       tl.symbol, " +
                    "       tl.transaction_type_fk, " +
                    "       sl.dividend " +
                    "FROM transaction_log tl " +
                    "         inner join dividend_log sl on tl.id = sl.transaction_fk " +
                    "WHERE account_fk = CAST(? AS uuid) " +
                    "  and tl.transaction_type_fk = 'DIVIDEND' " +
                    "  and symbol = ? " +
                    "ORDER BY date;";

    private JdbcTemplate jdbcTemplate;

    public DividendRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DividendJournalModel> getAllBySymbol(String accountId, String symbol) {
        Object[] parameters = new Object[] {accountId, symbol};
        return jdbcTemplate.query(DIVIDEND_READ_BY_SYMBOL_FOR_ACCOUNT, parameters, new DividendModelRowMapper());
    }
}