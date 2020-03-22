package com.hack.hipmon.configservice.data;

public class GroupModifyingRequest {
    private String modifyingType;
    private Group group;

    public GroupModifyingRequest(String modifyingType, Group group) {
        this.modifyingType = modifyingType;
        this.group = group;
    }

    public String getModifyingType() {
        return modifyingType;
    }

    public Group getGroup() {
        return group;
    }
}
