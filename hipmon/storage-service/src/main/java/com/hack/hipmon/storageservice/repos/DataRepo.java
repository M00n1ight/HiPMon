package com.hack.hipmon.storageservice.repos;

import com.hack.hipmon.storageservice.domain.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import ru.yandex.clickhouse.ClickHouseConnection;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataRepo {
    Logger logger = LoggerFactory.getLogger(DataRepo.class);

    private static final String SENSOR_ID = "sensor_id";
    private static final String VALUE = "value";
    private static final String TIMESTAMP = "CreationDate";

    private Environment env;

    private ClickHouseConnection connection;

    private final String url;
    private final String user;
    private final String password;

    private static final String selectAll = "SELECT * FROM Data" ;


    @Autowired
    public DataRepo(Environment env){
        this.env = env;

        logger.error("ENV is null? " + (env == null) );

        url = env.getProperty("spring.datasource.url");
        user = env.getProperty("spring.datasource.username");
        password = env.getProperty("spring.datasource.password");
    }



    public List<Data> getAll() throws SQLException {

        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(selectAll);

        List<Data> result = new ArrayList<>();

        while(rs.next()){
            result.add(
                    new Data(
                            rs.getInt(SENSOR_ID),
                            rs.getFloat(VALUE),
                            rs.getTimestamp(TIMESTAMP)
                            //rs.getDate("CreationDate")
                    )
            );
        }

        return result;
    }

    private static final String INSERT = "INSERT INTO Data (CreationDate, sensor_id, value) FORMAT Values ";
    public int put(List<Data> data) throws SQLException {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            Instant instant;
            StringBuilder query = new StringBuilder(INSERT);
            for (Data record : data) {
                query
                        .append("('").append(record.getTimestamp().getTime()).append("',")
                        .append(record.getId()).append(',')
                        .append(record.getValue()).append(')').append(',');
            }
            query.delete(query.length()-1, query.length());

            return connection.createStatement().executeUpdate(query.toString());
        }
    }

    /* CLICKHOUSE Batch doesn't work(
    private static final String INSERT_BATCH = "INSERT INTO Data (CreationDate, sensor_id, value) FORMAT Values (?, ?, ?);";
    public int pput(List<Data> data) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(INSERT_BATCH);
            for (Data record : data) {
                statement.setTimestamp(1, record.getTimestamp());
                statement.setInt(2, record.getId());
                statement.setFloat(3, record.getValue());
                statement.addBatch();
            }
            logger.error(statement.toString());
            return statement.executeBatch().length;
        }
    }*/
}
