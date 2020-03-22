package com.hack.hipmon.configservice.data;

public class ModificationRecord {
    private long timestamp;
    private String type;
    private String modification;

    public ModificationRecord(long timestamp, String type, String modification) {
        this.timestamp = timestamp;
        this.type = type;
        this.modification = modification;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getModification() {
        return modification;
    }
}
