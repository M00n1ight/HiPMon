package com.hack.hipmon.storageservice.controller;

import com.hack.hipmon.storageservice.client.ProxyServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyServiceController {

    @Autowired
    Environment env;

    @Autowired
    ProxyServiceClient client;

    @GetMapping("/test")
    public void test() {
        client.test();
    }


}