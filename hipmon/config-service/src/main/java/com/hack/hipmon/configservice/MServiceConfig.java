package com.hack.hipmon.configservice;

import com.hack.hipmon.configservice.storage.GroupsStorage;
import com.hack.hipmon.configservice.storage.QueryExecutor;
import com.hack.hipmon.configservice.storage.SensorsStorage;
import com.hack.hipmon.configservice.storage.TypesStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class MServiceConfig {

    @Bean
    public GroupsStorage getGroupsStorage() throws SQLException, ClassNotFoundException {
        return new GroupsStorage();
    }

    @Bean
    public SensorsStorage getSensorsStorage() throws SQLException, ClassNotFoundException {
        return new SensorsStorage();
    }

    @Bean
    public TypesStorage getTypesStorage() throws SQLException, ClassNotFoundException {
        return new TypesStorage();
    }

    @Bean
    public QueryExecutor getQueryExecutor() throws ClassNotFoundException {
        return new QueryExecutor();
    }
}
