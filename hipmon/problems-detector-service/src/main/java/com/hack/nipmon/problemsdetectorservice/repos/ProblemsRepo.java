package com.hack.nipmon.problemsdetectorservice.repos;

import com.hack.nipmon.problemsdetectorservice.domain.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ProblemsRepo {
    Logger logger = LoggerFactory.getLogger(ProblemsRepo.class);

    @Autowired
    private DataRepo repo;

    public List<Data> get(List<Integer> idS, Double startTimestamp, Double endTimestamp) throws SQLException {
        return repo.get(idS, startTimestamp,endTimestamp);
    }
    public int put(LinkedList<Data> data) throws SQLException {
        // TODO filterdata
        return repo.put(data);
    }
}
