package com.gary.testbatch.service;

import com.gary.testbatch.primary.Data;
import com.gary.testbatch.primary.DataRepository;

import com.gary.testbatch.secondary.MergeData;
import com.gary.testbatch.secondary.MergeDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PrimaryDBService {

    private static final Logger log = LoggerFactory.getLogger(PrimaryDBService.class);

    @Autowired
    DataRepository dataRepo;
    @Autowired
    MergeDataRepository mergeDataRepo;

    @Transactional(transactionManager = "primaryTransactionManager")
    public void insertTestData() {
        log.info("PrimaryDBService :: insertTestData Start");
        Data data = new Data();
        data.setDatetime(new Timestamp(123,5,11,11,30,0,0));
        data.setChannel(123);
        data.setValue(3.1415f);
        dataRepo.save(data);
        log.info("PrimaryDBService :: insertTestData End");
    }

    @Transactional(transactionManager = "primaryTransactionManager")
    public List<Data> getDataList() {
        return dataRepo.findAll();
    }

    @Transactional(transactionManager = "primaryTransactionManager")
    public void startMerge() {
        log.info("PrimaryDBService :: startMerge Start");
        long startTime = System.nanoTime();

        log.info("cleanupMergeDB Start");
        mergeDataRepo.deleteAllInBatch();
        log.info("cleanupMergeDB End");

        log.info("PrimaryDBService :: Get dataList Start");
        List<Data> dataList = dataRepo.findAll();
        log.info("PrimaryDBService :: Get dataList End, size :: " + dataList.size());

        log.info("insertDataToSecondDB Start, size :: " + dataList.size());
        List<MergeData> mergeDataList = new ArrayList<MergeData>();
        for(Data data : dataList) {
            MergeData mergeData = new MergeData();
            mergeData.setId(data.getId());
            mergeData.setChannel(data.getChannel());
            mergeData.setDatetime(data.getDatetime());
            mergeData.setValue(data.getValue());
            mergeDataList.add(mergeData);
        }
        mergeDataRepo.saveAll(mergeDataList);
        // TODO :: Update ID sequence of MergeData to max value
        // Insert Test Data
        //insertTestData();

        log.info("insertDataToSecondDB End, size :: " + dataList.size());

        log.info("PrimaryDBService :: startMerge End");
        long duration = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
        log.info("Duration in second :: " + duration);


    }
}
