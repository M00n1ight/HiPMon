package com.hack.hipmon.historystorageservice;

import com.hack.hipmon.historystorageservice.storage.HistoryStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class MServiceConfig {

    @Bean
    public HistoryStorage getHistoryStorage() throws SQLException, ClassNotFoundException {
        return new HistoryStorage();
    }
}
