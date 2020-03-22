package com.hack.nipmon.problemsdetectorservice.client;

import com.hack.nipmon.problemsdetectorservice.domain.SensorConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("sensors-storage-service")
@RibbonClient("sensors-storage-service")
public interface EuricaClient {

    @GetMapping("/sensors")
    List<SensorConfig> getConfigs();
}
