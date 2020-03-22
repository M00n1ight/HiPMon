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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class DataRepo {
    Logger logger = LoggerFactory.getLogger(DataRepo.class);

    private static final String SENSOR_ID = "sensor_id";
    private static final String VALUE = "value";
    //private static final String TIMESTAMP = "CreationDate";
    private static final String TIMESTAMP = "timestamp";

    private static final String selectAll = "SELECT * FROM Data" ;
    private static final String INSERT = new StringBuilder("INSERT INTO Data (")
            .append(TIMESTAMP).append(", ").append(SENSOR_ID).append(", ").append(VALUE)
            .append(") FORMAT Values").toString();

    private final String url;
    private final String user;
    private final String password;

    private Environment env;

    @Autowired
    public DataRepo(Environment env){
        this.env = env;

        url = env.getProperty("spring.datasource.url");
        user = env.getProperty("spring.datasource.username");
        password = env.getProperty("spring.datasource.password");
    }

    public List<Data> get(List<Integer> idS, Double startTimestamp, Double endTimestamp) throws SQLException{

        String query = prepareSelectStatement(idS, startTimestamp, endTimestamp);

        ResultSet resultSet;
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            resultSet = connection.createStatement().executeQuery(query);
        }

        List<Data> result = new ArrayList<>();

        while(resultSet.next()){
            result.add(
                    new Data(
                            resultSet.getInt(SENSOR_ID),
                            resultSet.getFloat(VALUE),
                            resultSet.getTimestamp(TIMESTAMP)
                    )
            );
        }

        return result;

    }

    private String prepareSelectStatement(List<Integer> idS, Double startTimestamp, Double endTimestamp) {
        StringBuilder idQuery = new StringBuilder();
        StringBuilder startTimestampQuery = new StringBuilder();
        StringBuilder endTimestampQuery = new StringBuilder();

        if(idS != null && !idS.isEmpty()){
            // sensor_id in (v1, ..., vN)
            logger.error("idS size " + idS.size());
            idQuery.append(SENSOR_ID).append(" in (")
                    .append(idS.stream().map(i->i.toString()).collect(Collectors.joining(", ")))
                    .append(')');
        }

        logger.error("Id " + idQuery.toString());

        if(startTimestamp != null){
            // CreateTime >= toDateTime('startTimestamp')
            startTimestampQuery.append(TIMESTAMP).append(" >= toDateTime64(").append(startTimestamp).append(", 3)");
        }
        logger.error("start " + startTimestampQuery.toString());

        if(endTimestamp != null){
            // CreateTime <= toDateTime('endTimestamp')
            endTimestampQuery.append(TIMESTAMP).append(" <= toDateTime64(").append(endTimestamp).append(", 3)");
        }
        logger.error("end " + endTimestampQuery.toString());

        String filter = Stream.of(idQuery, startTimestampQuery, endTimestampQuery)
                .filter(i -> i != null && !i.toString().isBlank())
                .collect(Collectors.joining(" AND "));

        logger.error("Filter " + filter);

        StringBuilder query = new StringBuilder(selectAll);
        if(!filter.isBlank())
            query.append(" WHERE ").append(filter);


        logger.error("Req " + query);

        return query.toString();
    }

    public int put(List<Data> data) throws SQLException {
        if (data != null && !data.isEmpty()) {
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                Instant instant;
                StringBuilder query = new StringBuilder(INSERT);
                for (Data record : data) {
                    logger.error("Time " + record.getTimestamp());
                    query
                            .append("('").append(record.getTimestamp()).append("',")
                            .append(record.getId()).append(',')
                            .append(record.getValue()).append(')').append(',');
                }
                query.delete(query.length() - 1, query.length());

                return connection.createStatement().executeUpdate(query.toString());
            }
        }
        return 0;
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
