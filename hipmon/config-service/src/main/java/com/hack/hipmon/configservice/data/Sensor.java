package com.hack.hipmon.configservice.data;

public class Sensor {
    private int id;
    private String name;
    private SensorType type;
    private Group group;
    private float bottomThreshold;
    private float topThreshold;

    public Sensor(int id, String name, SensorType type, Group group, float bottomThreshold, float topThreshold) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.group = group;
        this.bottomThreshold = bottomThreshold;
        this.topThreshold = topThreshold;
    }
    public Sensor(String name, SensorType type, Group group, float bottomThreshold, float topThreshold) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.group = group;
        this.bottomThreshold = bottomThreshold;
        this.topThreshold = topThreshold;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SensorType getType() {
        return type;
    }

    public Group getGroup() {
        return group;
    }

    public float getBottomThreshold() {
        return bottomThreshold;
    }

    public float getTopThreshold() {
        return topThreshold;
    }
}
