package com.example.tradelog.api.db.repository

import com.example.common.converter.TimeConverter
import com.example.tradelog.api.core.model.OptionJournalModel
import com.example.tradelog.api.core.model.TradeSummaryModel
import com.example.tradelog.api.db.converter.OptionJournalModelRowMapper
import com.example.tradelog.api.db.converter.TradeSummaryModelRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.sql.Connection

@Service
class OptionJournalRepository(private val jdbcTemplate: JdbcTemplate) : JournalRepository<OptionJournalModel> {

    companion object {
        private const val GET_SUMMARIES = """
           SELECT tl.symbol, ol.action_fk, ol.premium, tl.broker_fees, ol.contract_number, tl.transaction_type_fk
                FROM transaction_log tl
                        INNER JOIN option_log ol ON tl.id = ol.transaction_fk
                WHERE tl.account_fk = CAST(? AS uuid)
                  AND transaction_type_fk = 'OPTION'
                ORDER BY tl.symbol; 
        """

        private const val GET_BY_ID = """
            SELECT CAST(tl.id AS VARCHAR(36)),
                    CAST(tl.account_fk AS VARCHAR(36)),
                    tl.date,
                    tl.symbol,
                    tl.transaction_type_fk,
                    ol.stock_price,
                    ol.strike_price,
                    ol.expiry_date,
                    ol.contract_number,
                    ol.premium,
                    ol.action_fk,
                    ol.option_type_fk,
                    tl.broker_fees,
                    tsl.preferred_price,
                    tsl.group_selected,
                    tsl.leg_closed
                    FROM transaction_log tl
                      INNER JOIN option_log ol on tl.id = ol.transaction_fk
                      INNER JOIN transaction_settings_log tsl on tl.id = tsl.transaction_fk
                    WHERE tl.account_fk = CAST(? as uuid) AND tl.id = CAST(? AS uuid);
        """

        private const val GET_BY_SYMBOL = """
                SELECT CAST(tl.id AS VARCHAR(36)),
                    CAST(tl.account_fk AS VARCHAR(36)),
                    tl.date,
                    tl.symbol,
                    tl.transaction_type_fk,
                    ol.stock_price,
                    ol.strike_price,
                    ol.expiry_date,
                    ol.contract_number,
                    ol.premium,
                    ol.action_fk,
                    ol.option_type_fk,
                    tl.broker_fees,
                    tsl.preferred_price,
                    tsl.group_selected,
                    tsl.leg_closed
                    FROM transaction_log tl
                      INNER JOIN option_log ol on tl.id = ol.transaction_fk
                      INNER JOIN transaction_settings_log tsl on tl.id = tsl.transaction_fk
                    WHERE account_fk = CAST(? AS uuid) and symbol = ?
                    ORDER BY date;
        """

        private const val CREATE_RECORD = """
            INSERT INTO option_log (transaction_fk, stock_price, strike_price, expiry_date, contract_number, premium, action_fk, option_type_fk)
                    VALUES (CAST(? AS uuid), ?, ?, ?, ?, ?, ?, ?);
        """

        private const val EDIT_RECORD = """
            UPDATE option_log SET stock_price = ?, strike_price = ?, expiry_date = ?, contract_number = ?, premium = ?, action_fk = ?, option_type_fk = ? 
            WHERE transaction_fk = CAST(? AS uuid);
        """

        private const val DELETE_RECORD = "DELETE FROM option_log WHERE transaction_fk = CAST(? AS uuid) and option_type_fk in ('CALL', 'PUT')";
    }


    override fun getSummaries(accountId: String): List<TradeSummaryModel> {
        val parameters = arrayOf(accountId)
        return jdbcTemplate.query(GET_SUMMARIES, parameters, TradeSummaryModelRowMapper())
    }

    override fun createRecord(transactionId: String, model: OptionJournalModel) {
        jdbcTemplate.update { connection: Connection ->
            val ps = connection.prepareStatement(CREATE_RECORD)
            ps.setString(1, transactionId)
            ps.setDouble(2, model.stockPrice)
            ps.setDouble(3, model.strikePrice)
            ps.setTimestamp(4, TimeConverter.fromOffsetDateTime(model.expiryDate))
            ps.setInt(5, model.contracts)
            ps.setDouble(6, model.premium)
            ps.setString(7, model.action.name)
            ps.setString(8, model.optionType.name)
            ps
        }
    }

    override fun getById(accountId: String, transactionId: String): OptionJournalModel? {
        val parameters = arrayOf(accountId, transactionId)
        val models = jdbcTemplate.query(GET_BY_ID, parameters, OptionJournalModelRowMapper())
        if (models.size == 1) {
            return models[0]
        }
        return null
    }

    override fun getAllBySymbol(accountId: String, symbol: String): List<OptionJournalModel> {
        val parameters = arrayOf(accountId, symbol)
        return jdbcTemplate.query(GET_BY_SYMBOL, parameters, OptionJournalModelRowMapper())
    }

    override fun editRecord(model: OptionJournalModel): Boolean {
        return jdbcTemplate.update { connection: Connection ->
            val ps = connection.prepareStatement(EDIT_RECORD)
            ps.setDouble(1, model.stockPrice)
            ps.setDouble(2, model.strikePrice)
            ps.setTimestamp(3, TimeConverter.fromOffsetDateTime(model.expiryDate))
            ps.setInt(4, model.contracts)
            ps.setDouble(5, model.premium)
            ps.setString(6, model.action.name)
            ps.setString(7, model.optionType.name)
            ps.setString(8, model.transactionDetails.id)
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