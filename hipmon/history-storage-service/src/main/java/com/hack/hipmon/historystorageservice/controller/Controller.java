package com.hack.hipmon.historystorageservice.controller;

import com.hack.hipmon.historystorageservice.data.*;
import com.hack.hipmon.historystorageservice.storage.HistoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private HistoryStorage historyStorage;

    @GetMapping("/history")
    public List<ModificationRecord> getModifications(@RequestParam(required = false) Long from,
                                                     @RequestParam(required = false) Long to) {
        try {
            if (from != null || to != null) {
                return historyStorage.get(from, to);
            }
            return historyStorage.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    @PostMapping("/history/add")
    public Response addModification(@RequestBody ModificationRecord modificationRecord) {
        try {
            historyStorage.createModification(modificationRecord);
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
