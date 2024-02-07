package com.example.rent_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentServerApplication.class, args);
    }

}
