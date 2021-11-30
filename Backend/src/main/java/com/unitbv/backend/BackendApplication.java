package com.unitbv.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(BackendApplication.class);
        logger.info("Ana are mere!");
        SpringApplication.run(BackendApplication.class, args);

    }

}