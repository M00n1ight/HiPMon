package com.hack.hipmon.storageservice.controller;

import com.google.common.collect.ImmutableMap;
import com.hack.hipmon.storageservice.domain.Data;
import com.hack.hipmon.storageservice.repos.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class StorageServiceController {

    @Autowired
    private DataRepo repo;

    @GetMapping("/get")
    public List<Data> get(
            /*@RequestParam(required = false) Integer id,
            @RequestParam(required = false) Long startTimestamp,
            @RequestParam(required = false) Long endTimestamp*/
    ) throws SQLException {

        return repo.getAll();
    }

    /*@GetMapping("/get")
    public List<Data> get(@RequestParam String k) throws SQLException {

        return new ArrayList<>();
    }*/

    @PostMapping("/insert")
    public Map<Object, Object> post(@RequestBody List<Data> body) throws SQLException {
        repo.put(body);
        return successResponse;
    }

    private final Map<Object, Object> successResponse = ImmutableMap.of(
            "Result", true,
            "Description", "Success"
    );
}
