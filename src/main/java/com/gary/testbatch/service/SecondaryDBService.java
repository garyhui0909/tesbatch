package com.gary.testbatch.service;

import com.gary.testbatch.primary.Data;
import com.gary.testbatch.secondary.BatchMergeDataRepository;
import com.gary.testbatch.secondary.MergeData;
import com.gary.testbatch.secondary.MergeDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SecondaryDBService {

    private static final Logger log = LoggerFactory.getLogger(SecondaryDBService.class);

    @Autowired
    MergeDataRepository mergeDataRepo;
//    @Autowired
//    BatchMergeDataRepository batchMergeDataRepo;

    @Transactional(transactionManager = "secondaryTransactionManager")
    public void insertTestData() {
        log.info("SecondaryDBService :: insertTestData Start");
        MergeData data = new MergeData();
        data.setDatetime(new Timestamp(123,5,11,11,30,0,0));
        data.setChannel(123);
        data.setValue(3.1415f);
        mergeDataRepo.save(data);
        log.info("SecondaryDBService :: insertTestData End");
    }

    @Transactional(transactionManager = "secondaryTransactionManager")
    public void cleanupMergeDB() {
        log.info("SecondaryDBService :: cleanupMergeDB Start");
        mergeDataRepo.deleteAllInBatch();
        log.info("SecondaryDBService :: cleanupMergeDB End");
    }

    @Transactional(transactionManager = "secondaryTransactionManager")
    public void insertDataToSecondDB(List<Data> dataList) {
        log.info("SecondaryDBService :: insertDataToSecondDB Start");
        List<MergeData> mergeDataList = new ArrayList<MergeData>();
        for(Data data : dataList) {
            MergeData mergeData = new MergeData();
            BeanUtils.copyProperties(data, mergeData);
            mergeData.setcDistrict("aa");
            mergeDataList.add(mergeData);
//            if(mergeDataList.size() >= 100) break;
        }
        log.info("SecondaryDBService :: insertDataToSecondDB :: saveAll Start, size :: {}", mergeDataList.size());
//        mergeDataRepo.batchInsert(mergeDataList);
//        mergeDataRepo.batchInsertForNoPKEntity(mergeDataList, MergeData.class.getSimpleName());
        mergeDataRepo.batchInsertJdbc(mergeDataList, MergeData.class.getSimpleName());

        log.info("SecondaryDBService :: insertDataToSecondDB :: saveAll End");
        // TODO :: Update ID sequence of MergeData to max value
        // Insert Test Data
        //insertTestData();

        log.info("SecondaryDBService :: insertDataToSecondDB End");
    }

}
