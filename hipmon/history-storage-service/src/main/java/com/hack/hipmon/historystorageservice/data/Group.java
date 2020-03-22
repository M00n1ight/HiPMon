package com.hack.hipmon.historystorageservice.data;

public class Group {
    private int id;
    private String name;

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Group(String name) {
        this.name = name;
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

    public String toString() {
        return "{\"id\":" + id + ",\"name\":\"" + name + "\"}";
    }
}
