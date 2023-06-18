package com.gary.testbatch.secondary;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public abstract class BatchMergeDataRepository implements MergeDataRepository {

    private JdbcTemplate jdbcTemplate;

    public BatchMergeDataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
    @Transactional
    public void saveAll(List<MergeData> mergeDataList) {
        jdbcTemplate.batchUpdate("INSERT INTO data (id, datetime, channel, value) " +
                        "VALUES (?, ?, ?, ?)",
                mergeDataList,
                100,
                (PreparedStatement ps, MergeData mergeData) -> {
                    ps.setInt(1, mergeData.getId());
                    ps.setTimestamp(2, mergeData.getDatetime());
                    ps.setInt(3, mergeData.getChannel());
                    ps.setFloat(4, mergeData.getValue());
                });
    }
}
