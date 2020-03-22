package com.hack.hipmon.historystorageservice.data;

public class GroupModification extends Modification {
    private Group group;

    public GroupModification(String modificationType, long timestamp, Group group) {
        super(modificationType, timestamp);
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
}
