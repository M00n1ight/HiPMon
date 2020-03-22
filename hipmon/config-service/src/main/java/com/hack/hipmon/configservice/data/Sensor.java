package com.hack.hipmon.configservice.data;

public class Sensor {
    private Integer id;
    private String name;
    private SensorType type;
    private Group group;
    private Float bottomThreshold;
    private Float topThreshold;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Float getBottomThreshold() {
        return bottomThreshold;
    }

    public void setBottomThreshold(Float bottomThreshold) {
        this.bottomThreshold = bottomThreshold;
    }

    public Float getTopThreshold() {
        return topThreshold;
    }

    public void setTopThreshold(Float topThreshold) {
        this.topThreshold = topThreshold;
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
