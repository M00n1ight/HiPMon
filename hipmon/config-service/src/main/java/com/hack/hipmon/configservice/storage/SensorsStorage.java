package com.hack.hipmon.configservice.storage;

import com.hack.hipmon.configservice.data.Group;
import com.hack.hipmon.configservice.data.Sensor;
import com.hack.hipmon.configservice.data.SensorType;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class SensorsStorage {

    private Connection connection;
    private Statement statement;

    private static final String dbFile = "sensors.s3db";

    public SensorsStorage() throws SQLException, ClassNotFoundException {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

            statement = connection.createStatement();
            statement.execute("CREATE TABLE if not exists 'sensors' (" +
                    "'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "'name' text, " +
                    "'type' INT, " +
                    "'groupId' INT" +
                    "'bottomThreshold' FLOAT, " +
                    "'topThreshold' FLOAT);");

        }

    public List<Sensor> getAll() throws SQLException {
        List<Sensor> sensors = new LinkedList<>();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM sensors as ss INNER JOIN groups AS g ON ss.groupId = g.id INNER JOIN sensorTypes AS s ON ss.type = s.id");

        while (resultSet.next()) {
            sensors.add(wrapSensor(resultSet));
        }

        return sensors;
    }

    public void updateSensor(Sensor sensor) throws SQLException {
        statement.execute("UPDATE sensors SET " +
                "name='" + "'," +
                "type='" + "'," +
                "groupId='" + "'," +
                "bottomThreshold='" + "'," +
                "topThreshold='" + "' " +
                "WHERE id = " + sensor.getId() + ";");
    }

    public void createSensor(Sensor sensor) throws SQLException {
        statement.execute("INSERT INTO  sensors SET " +
                "name='" + sensor.getName() + "'," +
                "type='" + sensor.getType().getId() + "'," +
                "groupId='" + sensor.getGroup().getId()+ "'," +
                "bottomThreshold='" + sensor.getBottomThreshold() + "'," +
                "topThreshold='" + sensor.getTopThreshold() + "';");
    }

    private Sensor wrapSensor(ResultSet resultSet) throws SQLException {
        return new Sensor(resultSet.getInt("ss.id"),
                resultSet.getString("ss.name"),
                new SensorType(resultSet.getInt("ss.type"), resultSet.getString("t.name")),
                new Group(resultSet.getInt("ss.groupId"), resultSet.getString("g.name")),
                resultSet.getFloat("ss.topThreshold"),
                resultSet.getFloat("ss.bottomThreshold"));
    }
}
