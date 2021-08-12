package com.sie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class Application_Start {

    public static void main(String[] args) {
        SpringApplication.run(Application_Start.class,args);
    }
}
