package com.hack.hipmon.configservice.storage;

import com.hack.hipmon.configservice.data.Group;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GroupsStorage {

    private Connection connection;
    private Statement statement;

    private static final String dbFile = "groups.s3db";

    public GroupsStorage() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

        statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists 'groups' (" +
                "'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'name' text);");

    }

    public List<Group> getAll() throws SQLException {
        List<Group> groups = new LinkedList<>();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM groups;");

        while (resultSet.next()) {
            groups.add(wrapGroup(resultSet));
        }

        return groups;
    }

    public void createGroup(Group group) throws SQLException {
        statement.execute("INSERT INTO groups SET " +
                "name='" + group.getName() + "';");
    }

    public void updateGroup(Group group) throws SQLException {
        statement.execute("UPDATE groups SET " +
                "name='" + group.getName() + "'" +
                "WHERE id = " + group.getId() + ";");
    }

    private Group wrapGroup(ResultSet resultSet) throws SQLException {
        return new Group(resultSet.getInt("id"),
                resultSet.getString("name"));
    }
}
