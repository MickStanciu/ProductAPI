package com.example.tradelog.api.repository;

import com.example.tradelog.api.spec.model.ShareJournalModel;
import com.example.tradelog.api.spec.model.TradeSummaryModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class SharesJournalRepository {

    private static final String JOURNAL_READ_BY_SYMBOL_FOR_ACCOUNT =
            "SELECT CAST(tl.id AS VARCHAR(36)), " +
                    "       CAST(tl.account_fk AS VARCHAR(36)), " +
                    "       tl.date, " +
                    "       tl.symbol, " +
                    "       tl.transaction_type_fk, " +
                    "       sl.price, " +
                    "       sl.quantity, " +
                    "       sl.action_fk, " +
                    "       sl.action_type_fk, " +
                    "       sl.broker_fees " +
                    "FROM transaction_log tl " +
                    "         inner join shares_log sl on tl.id = sl.transaction_fk " +
                    "WHERE account_fk = CAST(? AS uuid) " +
                    "  and tl.transaction_type_fk = 'SHARE' " +
                    "  and symbol = ? " +
                    "ORDER BY date;";

    private static final String JOURNAL_READ_BY_TRANSACTION_ID =
            "SELECT CAST(tl.id AS VARCHAR(36)), " +
                    "       CAST(tl.account_fk AS VARCHAR(36)), " +
                    "       tl.date, " +
                    "       tl.symbol, " +
                    "       tl.transaction_type_fk, " +
                    "       sl.price, " +
                    "       sl.quantity, " +
                    "       sl.action_fk, " +
                    "       sl.action_type_fk, " +
                    "       sl.broker_fees " +
                    "FROM transaction_log tl " +
                    "         inner join shares_log sl on tl.id = sl.transaction_fk " +
                    "WHERE tl.id = CAST(? AS uuid);";

    private static final String JOURNAL_CREATE_SHARE_FOR_ACCOUNT =
            "INSERT INTO shares_log (transaction_fk, price, quantity, action_fk, action_type_fk, broker_fees) " +
                    "VALUES (CAST(? AS uuid), ?, ?, ?, ?, ?);";

    private static final String JOURNAL_DELETE_SHARE = "DELETE FROM shares_log WHERE transaction_fk = CAST(? AS uuid) and action_type_fk = 'STOCK'";

    private static final String JOURNAL_GET_SUMMARIES =
            "SELECT tl.symbol, sl.price, sl.broker_fees, sl.quantity, sl.action_fk " +
                    "FROM transaction_log tl" +
                    "         INNER JOIN shares_log sl ON tl.id = sl.transaction_fk " +
                    "WHERE tl.account_fk = CAST(? AS uuid)" +
                    "  AND transaction_type_fk = 'SHARE' " +
                    "ORDER BY symbol;";

    private JdbcTemplate jdbcTemplate;

    public SharesJournalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<ShareJournalModel> getAllBySymbol(String accountId, String symbol) {
        Object[] parameters = new Object[] {accountId, symbol};
        return jdbcTemplate.query(JOURNAL_READ_BY_SYMBOL_FOR_ACCOUNT, parameters, new ShareJournalModelRowMapper());
    }

    public Optional<ShareJournalModel> getByTransactionId(String transactionId) {
        Object[] parameters = new Object[] {transactionId};
        List<ShareJournalModel> modelList = jdbcTemplate.query(JOURNAL_READ_BY_TRANSACTION_ID, parameters, new ShareJournalModelRowMapper());
        if (modelList.size() == 1) {
            return Optional.ofNullable(modelList.get(0));
        }
        return Optional.empty();
    }


    /**
     * Creates a share record
     * @param id -
     * @param model -
     */
    public void createShareRecord(String id, ShareJournalModel model) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(JOURNAL_CREATE_SHARE_FOR_ACCOUNT);
            ps.setString(1,id);
            ps.setDouble(2, model.getPrice());
            ps.setInt(3, model.getQuantity());
            ps.setString(4, model.getAction().name());
            ps.setString(5, model.getActionType().name());
            ps.setDouble(6, model.getBrokerFees());
            return ps;
        });
    }


    /**
     * Deletes a share record
     * @param id -
     * @return true/false
     */
    public boolean deleteRecord(String id) {
        Object[] parameters = new Object[] {id};
        return jdbcTemplate.update(JOURNAL_DELETE_SHARE, parameters) == 1;
    }


    /**
     * Get list of share trade summaries
     * @param accountId -
     * @return -
     */
    public List<TradeSummaryModel> getSummaries(String accountId) {
        Object[] parameters = new Object[] {accountId};
        return jdbcTemplate.query(JOURNAL_GET_SUMMARIES, parameters, new TradeSummaryModelRowMapper());
    }
}
