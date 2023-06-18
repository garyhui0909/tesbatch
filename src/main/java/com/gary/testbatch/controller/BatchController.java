package com.gary.testbatch.controller;

import com.gary.testbatch.service.BizService;
import com.gary.testbatch.service.PrimaryDBService;
import com.gary.testbatch.service.SecondaryDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    PrimaryDBService primaryDBService;
    @Autowired
    SecondaryDBService secondaryDBService;
    @Autowired
    BizService bizService;

    private static final Logger log = LoggerFactory.getLogger(BatchController.class);

    @RequestMapping(value = "/startMerge", method = RequestMethod.POST)
    public void startMerge(
//            @RequestParam(value = "username") String username,
//            @RequestParam(value = "password") String password
    ) {

        log.info("StartMerge Start");

        bizService.startMerge();

        log.info("StartMerge End");

    }
}
