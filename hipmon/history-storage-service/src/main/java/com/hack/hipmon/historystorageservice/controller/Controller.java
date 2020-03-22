package com.hack.hipmon.historystorageservice.controller;

import com.hack.hipmon.historystorageservice.data.*;
import com.hack.hipmon.historystorageservice.storage.HistoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private HistoryStorage historyStorage;

    @GetMapping("/history")
    public List<ModificationRecord> getModifications() {
        try {
            return historyStorage.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    @PostMapping("/history/sensor")
    public Response addModification(@RequestBody SensorModification sensorModification) {
        try {
            historyStorage.createModification(sensorModification);
            return new Response(true, "success");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(false, e.getMessage());
        }
    }

    @PostMapping("/history/group")
    public Response addGroupModification(@RequestBody GroupModification groupModification) {
        try {
            historyStorage.createModification(groupModification);
            return new Response(true, "success");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(false, e.getMessage());
        }
    }

    public void setHistoryStorage(HistoryStorage historyStorage) {
        this.historyStorage = historyStorage;
    }
}
