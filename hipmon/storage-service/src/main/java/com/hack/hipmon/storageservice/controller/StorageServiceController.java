package com.hack.hipmon.storageservice.controller;

import com.google.common.collect.ImmutableMap;
import com.hack.hipmon.storageservice.domain.Data;
import com.hack.hipmon.storageservice.repos.DataRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class StorageServiceController {
    Logger logger = LoggerFactory.getLogger(StorageServiceController.class);

    @Autowired
    private DataRepo repo;

    @GetMapping("/route/data")
    public List<Data> get(
            @RequestParam(name="id",    required = false) ArrayList<Integer> idS,
            @RequestParam(name="start", required = false) Double startTimestamp,
            @RequestParam(name="end",   required = false) Double endTimestamp    ) throws SQLException {

        return repo.get(idS, startTimestamp, endTimestamp);
    }

    @PostMapping("/route/data")
    public Map<Object, Object> post(@RequestBody List<Data> body) throws SQLException {
        repo.put(body);
        return successResponse;
    }

    private final Map<Object, Object> successResponse = ImmutableMap.of(
            "Result", true,
            "Description", "Success"
    );
}
