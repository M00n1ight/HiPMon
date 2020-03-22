package com.hack.hipmon.configservice.storage;

import com.hack.hipmon.configservice.data.Group;
import com.hack.hipmon.configservice.data.Sensor;
import com.hack.hipmon.configservice.data.SensorType;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class SensorsStorage {


    @Autowired
    private QueryExecutor queryExecutor;
/*
CREATE TABLE if not exists 'sensors'
('id' INTEGER PRIMARY KEY AUTOINCREMENT,
'name' text,
'type' INT,
'groupId' INT,
'bottomThreshold' FLOAT,
'topThreshold' FLOAT,
FOREIGN KEY(groupId) REFERENCES groups(id),
FOREIGN KEY(type) REFERENCES sensorTypes(id));
*/

    public List<Sensor> getAll() throws SQLException {
        List<Sensor> sensors = new LinkedList<>();

        ResultSet resultSet = queryExecutor.executeQuery("SELECT * FROM sensors AS ss LEFT JOIN groups AS g ON ss.groupId = g.id LEFT JOIN sensorTypes AS s ON ss.type = s.id;");

        while (resultSet.next()) {
            sensors.add(wrapSensor(resultSet));
        }

        return sensors;
    }

    public void updateSensor(Sensor sensor) throws SQLException {
        queryExecutor.execute("UPDATE sensors SET " +
                "name='" + "'," +
                "type='" + "'," +
                "groupId='" + "'," +
                "bottomThreshold='" + "'," +
                "topThreshold='" + "' " +
                "WHERE id = " + sensor.getId() + ";");
    }

    public void createSensor(Sensor sensor) throws SQLException {
        queryExecutor.execute("INSERT INTO  sensors (name, type, groupId, bottomThreshold, topThreshold) VALUES (" +
                "'" + sensor.getName() + "'," +
                "'" + sensor.getType().getId() + "'," +
                "'" + sensor.getGroup().getId()+ "'," +
                "'" + sensor.getBottomThreshold() + "'," +
                "'" + sensor.getTopThreshold() + "');");
    }

    private Sensor wrapSensor(ResultSet resultSet) throws SQLException {
        Sensor s = new Sensor();
        s.setId(resultSet.getInt(1));
        s.setName(resultSet.getString(2));
        SensorType st = new SensorType();
        st.setId(resultSet.getInt(3));
        st.setName(resultSet.getString(10));
        s.setType(st);
        Group g = new Group();
        g.setId(resultSet.getInt(4));
        g.setName(resultSet.getString(8));
        s.setGroup(g);
        s.setBottomThreshold(resultSet.getFloat(5));
        s.setTopThreshold(resultSet.getFloat(6));
        return s;
    }
}
