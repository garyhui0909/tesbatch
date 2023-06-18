package com.gary.testbatch.config;

//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
////import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


//@Configuration
//@EnableBatchProcessing
public class BatchConfig {


//    @Override
//    public void setDataSource(DataSource dataSource) {
//        // override to do not set datasource even if a datasource exist.
//        // initialize will use a Map based JobRepository (instead of database)
//    }

    //    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;

//    @Autowired
//    MyCustomReader myCustomReader;
//
//    @Autowired
//    MyCustomWriter myCustomWriter;
//
//    @Autowired
//    MyCustomProcessor myCustomProcessor;
//
//    @Bean
//    public Job createJob() {
//        return jobBuilderFactory.get("MyJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(createStep()).end().build();
//    }
//
//    @Bean
//    public Step createStep() {
//        return stepBuilderFactory.get("MyStep")
//                .<Employee, Manager> chunk(1)
//                .reader(myCustomReader)
//                .processor(myCustomProcessor)
//                .writer(myCustomWriter)
//                .build();
//    }
}