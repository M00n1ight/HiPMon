package com.hack.hipmon.storageservice.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "proxy-service")
@RibbonClient(name = "proxy-service")
public interface ProxyServiceClient {

    @GetMapping("/hello/")
    public String test();

}