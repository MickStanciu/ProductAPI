package com.example.tradelog.api.repository;

import com.example.common.converter.TimeConversion;
import com.example.tradelog.api.spec.model.ShareDataModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShareDataModelRowMapper implements RowMapper<ShareDataModel> {

    @Override
    public ShareDataModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ShareDataModel.builder()
                .withSymbol(rs.getString("symbol"))
                .withLastUpdatedOn(TimeConversion.fromTimestamp(rs.getTimestamp("last_updated_on")))
                .withSector(rs.getString("sector"))
                .withPrice(rs.getDouble("price"))
                .withMarketCapitalization(rs.getDouble("market_cap_b"))
                .withPeRatio(rs.getDouble("p_e_ratio"))
                .withPeRatioFuture(rs.getDouble("future_p_e_ratio"))
                .withBookValue(rs.getDouble("book_value"))
                .withEps(rs.getDouble("eps"))
                .withEpsFuture(rs.getDouble("future_eps"))
                .withFinvizTarget(rs.getBigDecimal("finviz_target"))
                .withCalculatedTarget(rs.getBigDecimal("p_e_future_target"))
                .build();
    }
}