package com.hack.nipmon.problemsdetectorservice.domain;

import java.util.StringJoiner;

public class SensorConfig {
    private int id;
    private String name;
    private Type type;
    private Group group;
    private Float topThreshold;
    private Float bottomThreshold;

    public SensorConfig(int id,
                        String name,
                        Type type,
                        Group group,
                        Float bottomThreshold,
                        Float topThreshold) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.group = group;
        this.bottomThreshold = bottomThreshold;
        this.topThreshold = topThreshold;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SensorConfig.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("topThreshold=" + topThreshold)
                .add("bottomThreshold=" + bottomThreshold)
                .toString();
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Group getGroup() {
        return group;
    }

    public Float getTopThreshold() {
        return topThreshold;
    }

    public Float getBottomThreshold() {
        return bottomThreshold;
    }

    public static class Type{
        private Integer id;
        private String name;

        public Type(Integer id, String name){
            this.id = id;
            this.name = name;
        }

        public Integer getId() { return id; }

        public String getName() { return name; }
    }
    public static class Group {
        private Integer id;
        private String name;

        public Group(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() { return id; }

        public String getName() { return name; }
    }
}
