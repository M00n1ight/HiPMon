package com.hack.hipmon.configservice.storage;

import com.hack.hipmon.configservice.data.SensorType;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TypesStorage {

    @Autowired
    private QueryExecutor queryExecutor;

/*
CREATE TABLE if not exists sensorTypes (
'id' INTEGER PRIMARY KEY,
'name' text);
}*/

    public List<SensorType> getAll() throws SQLException {
        List<SensorType> types = new LinkedList<>();

        ResultSet resultSet = queryExecutor.executeQuery("SELECT * FROM sensorTypes;");

        while (resultSet.next()) {
            types.add(wrapSensorType(resultSet));
        }

        return types;
    }

    private SensorType wrapSensorType(ResultSet resultSet) throws SQLException {
        SensorType st = new SensorType();
        st.setId(resultSet.getInt("id"));
        st.setName(resultSet.getString("name"));
        return st;
    }

}
