package com.hack.hipmon.configservice.storage;

import com.hack.hipmon.configservice.data.SensorType;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TypesStorage {

    private Connection connection;
    private Statement statement;

    private static final String dbFile = "sensors.s3db";

    public TypesStorage() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

        statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists sensorTypes (" +
                "'id' INTEGER PRIMARY KEY, " +
                "'name' text);");

        initIfEmptyTable();
    }

    public List<SensorType> getAll() throws SQLException {
        List<SensorType> types = new LinkedList<>();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM sensorTypes;");

        while (resultSet.next()) {
            types.add(wrapSensorType(resultSet));
        }

        return types;
    }

    private void initIfEmptyTable() throws SQLException {
        ResultSet r = statement.executeQuery("SELECT COUNT(*) FROM sensorTypes;");
        r.next();

        if (r.getInt(1) == 0) {
            statement.execute("INSERT INTO sensorTypes VALUES (1, 'q');");
            statement.execute("INSERT INTO sensorTypes VALUES (2, 'w');");
            statement.execute("INSERT INTO sensorTypes VALUES (3, 'e');");
            statement.execute("INSERT INTO sensorTypes VALUES (4, 'r');");
            statement.execute("INSERT INTO sensorTypes VALUES (5, 't');");
            statement.execute("INSERT INTO sensorTypes VALUES (6, 'y');");
            statement.execute("INSERT INTO sensorTypes VALUES (7, 'u');");
            statement.execute("INSERT INTO sensorTypes VALUES (8, 'i');");
        }
    }

    private SensorType wrapSensorType(ResultSet resultSet) throws SQLException {
        return new SensorType(resultSet.getInt("id"),
                resultSet.getString("name"));
    }

}
