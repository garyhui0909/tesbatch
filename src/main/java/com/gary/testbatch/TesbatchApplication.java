package com.gary.testbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gary")
@EnableAutoConfiguration
public class TesbatchApplication {

	private static final Logger log = LoggerFactory.getLogger(TesbatchApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TesbatchApplication.class, args);
		log.info("Application started");
	}

}
