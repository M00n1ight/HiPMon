package com.hack.nipmon.problemsdetectorservice;

import com.hack.nipmon.problemsdetectorservice.repos.SensorConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ProblemsDetectorServiceApplication {

    @Autowired
    static SensorConfigRepo repo;



    public static void main(String[] args) {

        //repo.reloadRepo();

        SpringApplication.run(ProblemsDetectorServiceApplication.class, args);
    }

}
