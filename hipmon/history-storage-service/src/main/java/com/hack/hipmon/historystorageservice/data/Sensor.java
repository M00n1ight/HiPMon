package com.hack.hipmon.historystorageservice.data;

public class Sensor {
    private Integer id;
    private String name;
    private SensorType type;
    private Group group;
    private Float bottomThreshold;
    private Float topThreshold;

    public Sensor(int id, String name, SensorType type, Group group, float bottomThreshold, float topThreshold) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.group = group;
        this.bottomThreshold = bottomThreshold;
        this.topThreshold = topThreshold;
    }
    public Sensor(String name, SensorType type, Group group, float bottomThreshold, float topThreshold) {
        id = null;
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

    @Override
    public String toString() {
        return new StringBuilder().append("{")
                .append("\"id\":").append(id == null ? "null" : id).append(",")
                .append("\"name\":\"").append(name == null ? "" : name).append(",")
                .append("\"type\":").append(type == null ? "null" : type.toString()).append(",")
                .append("\"group\":").append(group == null ? "null" : group.toString()).append(",")
                .append("\"bottomThreshold\":").append(bottomThreshold == null ? "null" : bottomThreshold).append(",")
                .append("\"topThreshold\":").append(topThreshold == null ? "null" : topThreshold)
                .append("}")
                .toString();
    }
}
