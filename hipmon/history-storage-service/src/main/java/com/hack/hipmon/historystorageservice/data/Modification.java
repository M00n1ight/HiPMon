package com.hack.hipmon.historystorageservice.data;

public class Modification {
    private String type;
    private long timestamp;

    public Modification(String type, long timestamp) {
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
