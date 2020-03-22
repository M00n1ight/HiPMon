package com.hack.nipmon.problemsdetectorservice.domain;

import java.sql.Timestamp;

public class Data {
    private final int id;
    private final float value;
    private final Timestamp timestamp;

    public Data(int id, float value, Timestamp timestamp){
        this.id = id;
        this.value = value;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }

    public int getId() { return id; }

    public float getValue() { return value; }

    public Timestamp getTimestamp() { return timestamp; }
}
