package com.hack.hipmon.configservice.controller;

import com.hack.hipmon.configservice.data.Response;
import com.hack.hipmon.configservice.data.Sensor;
import com.hack.hipmon.configservice.data.SensorType;
import com.hack.hipmon.configservice.storage.SensorsStorage;
import com.hack.hipmon.configservice.storage.TypesStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RestController
public class SensorsController {

    @Autowired
    private SensorsStorage sensorsStorage;

    @Autowired
    private TypesStorage typesStorage;

    @GetMapping("/sensors")
    public List<Sensor> getSensorsConfig() {
        try {
            return sensorsStorage.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    @PostMapping("/sensors/{id}")
    public Response putSensor(@RequestBody Sensor sensor, @PathVariable(required = false) Integer id) {
        if (id != null) {
            sensor.setId(id);
            try {
                sensorsStorage.updateSensor(sensor);
                return new Response(true, "success");
            } catch (SQLException e) {
                e.printStackTrace();
                return new Response(false, e.getMessage());
            }
        } else {
            try {
                sensorsStorage.createSensor(sensor);
                return new Response(true, "success");
            } catch (SQLException e) {
                e.printStackTrace();
                return new Response(false, e.getMessage());
            }
        }
    }

    @GetMapping("/sensors/types")
    public List<SensorType> getTypes() {
        try {
            return typesStorage.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    public void setSensorsStorage(SensorsStorage sensorsStorage) {
        this.sensorsStorage = sensorsStorage;
    }
}
