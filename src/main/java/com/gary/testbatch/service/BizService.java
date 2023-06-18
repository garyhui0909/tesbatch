package com.gary.testbatch.service;

import com.gary.testbatch.primary.Data;
import com.gary.testbatch.primary.DataRepository;
import com.gary.testbatch.secondary.MergeData;
import com.gary.testbatch.secondary.MergeDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BizService {

    private static final Logger log = LoggerFactory.getLogger(BizService.class);

    @Autowired
    private PrimaryDBService primaryDBService;
    @Autowired
    private SecondaryDBService secondaryDBService;
    @Autowired
    DataRepository dataRepo;
    @Autowired
    MergeDataRepository mergeDataRepo;


    public void startMerge() {

        log.info("BizService :: startMerge Start");
        long startTime = System.nanoTime();

        log.info("cleanupMergeDB Start");
        secondaryDBService.cleanupMergeDB();
        log.info("cleanupMergeDB End");

        final int BATCH_SIZE = 1000;
        int totalInserted = 0;
        int pageIndex = 0;
        Slice<Data> slice;
        do {
            log.info("Get dataList Start");
            slice = dataRepo.findAll(PageRequest.of(pageIndex, BATCH_SIZE));
            List<Data> dataList = slice.getContent();
            log.info("Get dataList End with size {}, insertDataToSecondDB start", dataList.size());
            secondaryDBService.insertDataToSecondDB(dataList);
            totalInserted += dataList.size();
            pageIndex++;
            log.info("insertDataToSecondDB End, size: {}, total inserted: {} ", dataList.size(), totalInserted);
        } while (slice.hasNext());


//        log.info("PrimaryDBService :: Get dataList first time Start");
//        Slice<Data> slice = dataRepo.findAll(PageRequest.of(0, BATCH_SIZE));
//        List<Data> dataList = slice.getContent();
//        totalInserted += dataList.size();
//        log.info("PrimaryDBService :: Get dataList first time End, size {}" ,dataList.size());
//
//        log.info("insertDataToSecondDB Start, size :: " + dataList.size());
//        secondaryDBService.insertDataToSecondDB(dataList);
//        log.info("insertDataToSecondDB End, size: {}, total inserted: {} " + dataList.size(), totalInserted);
//
//        while(slice.hasNext()) {
//            log.info("PrimaryDBService :: Get dataList Start");
//            slice = dataRepo.findAll(slice.nextPageable());
//            dataList = slice.getContent();
//            totalInserted += dataList.size();
//            log.info("PrimaryDBService :: Get dataList End, size {}" ,dataList.size());
//            log.info("insertDataToSecondDB Start, size :: " + dataList.size());
//            secondaryDBService.insertDataToSecondDB(dataList);
//            log.info("insertDataToSecondDB End, size: {}, total inserted: {} " ,dataList.size(), totalInserted);
//        }

        // TODO :: Update ID sequence of MergeData to max value
        // Insert Test Data
        //insertTestData();

        log.info("Total inserted Data To Second DB :: " + totalInserted);

        log.info("BizService :: startMerge End");
        long duration = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
        log.info("Duration in second :: " + duration);
    }

    public void startMerge2() {
        log.info("BizService :: StartMerge Start");
        long startTime = System.nanoTime();

        log.info("cleanupMergeDB Start");
        secondaryDBService.cleanupMergeDB();
        log.info("cleanupMergeDB End");

        log.info("Get dataList from primary DB Start");
        List<Data> dataList = primaryDBService.getDataList();
        log.info("Get dataList from primary DB End, size :: " + dataList.size());

        log.info("Insert dataList to secondary DB Start");
        secondaryDBService.insertDataToSecondDB(dataList);
        log.info("Insert dataList to secondary DB End, size :: " + dataList.size());

        log.info("BizService :: StartMerge End");
        long duration = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
        log.info("Duration in second :: " + duration);
    }
}
