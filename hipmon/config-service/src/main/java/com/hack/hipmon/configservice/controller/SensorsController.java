package com.hack.hipmon.configservice.controller;

import com.hack.hipmon.configservice.Client;
import com.hack.hipmon.configservice.data.ModificationRecord;
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

    @Autowired
    private Client client;

    @GetMapping("/sensors")
    public List<Sensor> getSensors(@RequestParam(required = false) Integer groupId) {
        try {
            if (groupId != null) {
                return sensorsStorage.getByGroupId(groupId);
            }
            return sensorsStorage.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    @PostMapping("/sensors/update")
    public Response updateSensor(@RequestBody Sensor sensor) {
        try {
            sensorsStorage.updateSensor(sensor);
            client.sendModificationRecord(new ModificationRecord(System.currentTimeMillis(),"update", sensor.toString()));
            return new Response(true, "success");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(false, e.getMessage());
        }
    }

    @PostMapping("/sensors/add")
    public Response addSensor(@RequestBody Sensor sensor) {
        try {
            sensorsStorage.createSensor(sensor);
            client.sendModificationRecord(new ModificationRecord(System.currentTimeMillis(),"create", sensor.toString()));
            return new Response(true, "success");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(false, e.getMessage());
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
