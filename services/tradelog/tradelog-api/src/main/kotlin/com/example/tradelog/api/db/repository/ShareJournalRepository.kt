package com.example.tradelog.api.db.repository

import com.example.common.error.DataAccessError
import com.example.common.types.Either
import com.example.tradelog.api.core.model.ShareJournalModel
import com.example.tradelog.api.core.model.TradeSummaryModel
import com.example.tradelog.api.db.converter.ShareJournalModelRowMapper
import com.example.tradelog.api.db.converter.TradeSummaryModelRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.sql.Connection

@Service
class ShareJournalRepository(private val jdbcTemplate: JdbcTemplate) : JournalRepository<ShareJournalModel> {

    companion object {
        private const val GET_SUMMARIES = """
            SELECT tl.symbol,
                   sl.price,
                   tl.broker_fees,
                   sl.quantity,
                   sl.action_fk,
                   tl.transaction_type_fk,
                   tsl.leg_closed
            FROM transaction_log tl
                     INNER JOIN shares_log sl ON tl.id = sl.transaction_fk
                     INNER JOIN transaction_settings_log tsl on tl.id = tsl.transaction_fk
            WHERE tl.account_fk = CAST(? AS uuid)
                  AND tl.transaction_type_fk = 'SHARE'
            ORDER BY symbol;
        """

        private const val GET_BY_SYMBOL = """
            SELECT CAST(tl.id AS VARCHAR(36)),
                               CAST(tl.account_fk AS VARCHAR(36)),
                               CAST(tl.portfolio_fk AS VARCHAR(36)),
                               tl.date,
                               tl.symbol,
                               tl.transaction_type_fk,
                               sl.price,
                               sl.quantity,
                               sl.action_fk,
                               tl.broker_fees,
                               tsl.preferred_price,
                               tsl.group_selected,
                               tsl.leg_closed
                        FROM transaction_log tl
                                 INNER JOIN shares_log sl ON tl.id = sl.transaction_fk
                                 INNER JOIN transaction_settings_log tsl ON tl.id = tsl.transaction_fk
                        WHERE account_fk = CAST(? AS uuid)
                            AND tl.portfolio_fk = CAST(? AS uuid)
                            AND tl.transaction_type_fk = 'SHARE'
                            AND tl.symbol = ?
                        ORDER BY date;
        """

        private const val GET_BY_ID = """
            SELECT CAST(tl.id AS VARCHAR(36)),
                           CAST(tl.account_fk AS VARCHAR(36)),
                           CAST(tl.portfolio_fk AS VARCHAR(36)),
                           tl.date,
                           tl.symbol,
                           tl.transaction_type_fk,
                           sl.price,
                           sl.quantity,
                           sl.action_fk,
                           tl.broker_fees,
                           tsl.preferred_price,
                           tsl.group_selected,
                           tsl.leg_closed
                    FROM transaction_log tl
                             INNER JOIN shares_log sl ON tl.id = sl.transaction_fk
                             INNER JOIN transaction_settings_log tsl ON tl.id = tsl.transaction_fk
                    WHERE tl.account_fk = CAST(? as uuid) 
                        AND tl.id = CAST(? AS uuid);
        """

        private const val CREATE_RECORD = "INSERT INTO shares_log (transaction_fk, price, quantity, action_fk) VALUES (CAST(? AS uuid), ?, ?, ?);"

        private const val EDIT_RECORD = "UPDATE shares_log SET price = ?, quantity = ?, action_fk = ? WHERE transaction_fk = CAST(? AS uuid)"

        private const val DELETE_RECORD = "DELETE FROM shares_log WHERE transaction_fk = CAST(? AS uuid)"

    }

    override fun getSummaries(accountId: String): List<TradeSummaryModel> {
        val parameters = arrayOf(accountId)
        return jdbcTemplate.query(GET_SUMMARIES, parameters, TradeSummaryModelRowMapper())
    }

    override fun createRecord(transactionId: String, model: ShareJournalModel) {
        jdbcTemplate.update { connection: Connection ->
            val ps = connection.prepareStatement(CREATE_RECORD)
            ps.setString(1, transactionId)
            ps.setDouble(2, model.price)
            ps.setInt(3, model.quantity)
            ps.setString(4, model.action.name)
            ps
        }
    }

    override fun getById(accountId: String, transactionId: String): Either<DataAccessError, ShareJournalModel> {
        val parameters = arrayOf<Any>(accountId, transactionId)
        val models = jdbcTemplate.query(GET_BY_ID, parameters, ShareJournalModelRowMapper())
        if (models.size == 1) {
            return Either.Value(models[0])
        }
        return Either.Error(DataAccessError.RecordNotFound("Couldn't find share record with id $transactionId"))
    }

    override fun getAllBySymbol(accountId: String, portfolioId: String, symbol: String): List<ShareJournalModel> {
        val parameters = arrayOf<Any>(accountId, portfolioId, symbol)
        return jdbcTemplate.query(GET_BY_SYMBOL, parameters, ShareJournalModelRowMapper())
    }

    override fun editRecord(model: ShareJournalModel): Boolean {
        return jdbcTemplate.update { connection: Connection ->
            val ps = connection.prepareStatement(EDIT_RECORD)
            ps.setDouble(1, model.price)
            ps.setInt(2, model.quantity)
            ps.setString(3, model.action.name)
            ps.setString(4, model.transactionDetails.id)
            ps
        } == 1
    }

    override fun deleteRecord(transactionId: String): Boolean {
        return jdbcTemplate.update { connection: Connection ->
            val ps = connection.prepareStatement(DELETE_RECORD)
            ps.setString(1, transactionId)
            ps
        } == 1
    }
}
