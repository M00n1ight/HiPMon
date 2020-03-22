package com.hack.hipmon.historystorageservice.data;

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

    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"name\":\"" + name + "\"}";
    }
}
