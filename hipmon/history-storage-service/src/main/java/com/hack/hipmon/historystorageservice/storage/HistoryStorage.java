package com.hack.hipmon.historystorageservice.storage;

import com.hack.hipmon.historystorageservice.data.ModificationRecord;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class HistoryStorage {

    private Statement statement;

    private static final String dbFile = "history.s3db";

    public HistoryStorage() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

        statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists 'history' (" +
                "'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'timestamp' INT, " +
                "'type' text, " +
                "'modification' text);");

    }

    public List<ModificationRecord> getAll() throws SQLException {
        List<ModificationRecord> records = new LinkedList<>();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM history;");

        while (resultSet.next()) {
            records.add(wrapModificationRecord(resultSet));
        }

        return records;
    }

    public void createModification(ModificationRecord modificationRecord) throws SQLException {
        statement.execute("INSERT INTO history (type, modification, timestamp) VALUES (" +
                "'" + modificationRecord.getType() + "'," +
                "'" + modificationRecord.getModification() + "'," +
                "" + modificationRecord.getTimestamp() + ");");
    }

    private ModificationRecord wrapModificationRecord(ResultSet resultSet) throws SQLException {
        return new ModificationRecord(resultSet.getLong("timestamp"),
                resultSet.getString("type"),
                resultSet.getString("modification"));
    }
}
