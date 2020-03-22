package com.hack.hipmon.configservice.data;

public class Response {
    private boolean result;
    private String description;

    public Response(boolean result, String description) {
        this.result = result;
        this.description = description;
    }

    public boolean isResult() {
        return result;
    }

    public String getDescription() {
        return description;
    }
}
