package com.hack.hipmon.configservice;

import com.hack.hipmon.configservice.data.ModificationRecord;
import com.hack.hipmon.configservice.data.Response;
import com.hack.hipmon.configservice.data.Sensor;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("history-storage-service")
@RibbonClient("history-storage-service")
public interface Client {

    @PostMapping("/history/add")
    Response sendModificationRecord(ModificationRecord modificationRecord);

}
