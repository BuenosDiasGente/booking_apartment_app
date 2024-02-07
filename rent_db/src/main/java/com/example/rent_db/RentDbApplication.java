package com.example.rent_db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableScheduling
public class RentDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentDbApplication.class, args);
    }

}
