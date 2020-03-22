package com.hack.hipmon.configservice.storage;

import java.sql.*;

public class QueryExecutor {


    private static final String dbFile = "sensors.s3db";
    private String url;

    public QueryExecutor() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        url = "jdbc:sqlite:" + dbFile;
    }

    public synchronized ResultSet executeQuery(String query) throws SQLException {
        Connection connection = DriverManager.getConnection(url);

        Statement statement = connection.createStatement();
        ResultSet r = statement.executeQuery(query);
        return r;
    }

    public synchronized void execute(String query) throws SQLException {
        Connection connection = DriverManager.getConnection(url);

        Statement statement = connection.createStatement();
        statement.execute(query);
    }
}
