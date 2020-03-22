package com.hack.nipmon.problemsdetectorservice.controller;

import com.google.common.collect.ImmutableMap;
import com.hack.nipmon.problemsdetectorservice.client.EuricaClient;
import com.hack.nipmon.problemsdetectorservice.domain.Data;
import com.hack.nipmon.problemsdetectorservice.repos.ProblemsRepo;
import com.hack.nipmon.problemsdetectorservice.repos.SensorConfigRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class ProblemsDetectorServiceController {
    Logger logger = LoggerFactory.getLogger(ProblemsDetectorServiceController.class);

    @Autowired
    private ProblemsRepo repo;

    @Autowired
    private SensorConfigRepo configRepo;

    @GetMapping("/problems")
    public List<Data> get(
            @RequestParam(name="id",    required = false) ArrayList<Integer> idS,
            @RequestParam(name="start", required = false) Double startTimestamp,
            @RequestParam(name="end",   required = false) Double endTimestamp    ) throws SQLException {

        return repo.get(idS, startTimestamp, endTimestamp);
    }

    private final Map<Object, Object> successResponse = ImmutableMap.of(
            "Result", true,
            "Description", "Success"
    );

    @PostMapping("/problems")
    public Map<Object, Object> post(@RequestBody LinkedList<Data> body) throws SQLException {
        repo.put(body);
        return successResponse;
    }

    @PostMapping("problems/reloadconfig")
    public Map<Object, Object> reloadConfig() {
        configRepo.reloadRepo();
        return successResponse;
    }

}
