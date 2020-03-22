package com.hack.hipmon.configservice.data;

public class SensorModifyingRequest {
    private String modifyingType;
    private Sensor sensor;

    public SensorModifyingRequest(String modifyingType, Sensor sensor) {
        this.modifyingType = modifyingType;
        this.sensor = sensor;
    }

    public String getModifyingType() {
        return modifyingType;
    }

    public Sensor getSensor() {
        return sensor;
    }
}
