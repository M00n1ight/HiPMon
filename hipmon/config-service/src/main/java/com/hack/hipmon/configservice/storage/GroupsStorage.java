package com.hack.hipmon.configservice.storage;

import com.hack.hipmon.configservice.data.Group;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GroupsStorage {


    @Autowired
    private QueryExecutor queryExecutor;
/*
CREATE TABLE if not exists 'groups' (
'id' INTEGER PRIMARY KEY AUTOINCREMENT,
'name' text);
}*/

    public List<Group> getAll() throws SQLException {
        List<Group> groups = new LinkedList<>();

        ResultSet resultSet = queryExecutor.executeQuery("SELECT * FROM groups;");

        while (resultSet.next()) {
            groups.add(wrapGroup(resultSet));
        }

        return groups;
    }

    public void createGroup(Group group) throws SQLException {
        queryExecutor.execute("INSERT INTO groups (name) VALUES ('" + group.getName() + "');");
    }

    public void updateGroup(Group group) throws SQLException {
        queryExecutor.execute("UPDATE groups SET " +
                "name='" + group.getName() + "'" +
                "WHERE id = " + group.getId() + ";");
    }

    private Group wrapGroup(ResultSet resultSet) throws SQLException {
        Group g = new Group();
        g.setId(resultSet.getInt("id"));
        g.setName(resultSet.getString("name"));
        return g;
    }

    public void setQueryExecutor(QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }


}
