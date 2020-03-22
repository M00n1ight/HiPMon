package com.hack.hipmon.historystorageservice.data;

public class SensorModification extends Modification {

    private Sensor sensor;

    public SensorModification(String modificationType, long timestamp, Sensor sensor) {
        super(modificationType, timestamp);
        this.sensor = sensor;
    }

    public Sensor getSensor() {
        return sensor;
    }
}
