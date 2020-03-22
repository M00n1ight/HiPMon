package com.hack.hipmon.configservice.controller;

import com.hack.hipmon.configservice.data.Group;
import com.hack.hipmon.configservice.data.Response;
import com.hack.hipmon.configservice.storage.GroupsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RestController
public class GroupsController {

    @Autowired
    private GroupsStorage groupsStorage;

    @GetMapping("/sensors/groups")
    public List<Group> getGroups() {
        try {
            return groupsStorage.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    @PostMapping("/sensors/groups/add")
    public Response addSensor(@RequestBody Group group) {
        try {
            groupsStorage.createGroup(group);
            return new Response(true, "success");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(false, e.getMessage());
        }
    }

    @PostMapping("/sensors/groups/update")
    public Response putSensor(@RequestBody Group group) {
        try {
            groupsStorage.updateGroup(group);
            return new Response(true, "success");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(false, e.getMessage());
        }
    }


    public void setGroupsStorage(GroupsStorage groupsStorage) {
        this.groupsStorage = groupsStorage;
    }
}
