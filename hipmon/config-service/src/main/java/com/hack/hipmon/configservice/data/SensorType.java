package com.hack.hipmon.configservice.data;

public class SensorType {
    private int id;
    private String name;

    public SensorType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
