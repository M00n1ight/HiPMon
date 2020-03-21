package com.hack.hipmon.storageservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageServiceController {

    @Autowired
    Environment env;

    @GetMapping("/hello")
    public String hello() {
        return env.getProperty("server.port");
    }

}
