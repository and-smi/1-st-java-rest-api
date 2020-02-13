package com.example.demo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataBaseConfig {

    public static DataSource CreateDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/organdstaff");
        hikariConfig.setPassword("sa1");
        hikariConfig.setUsername("postgres");
        hikariConfig.setAutoCommit(true);
        hikariConfig.setMaximumPoolSize(32);
        return new HikariDataSource(hikariConfig);
    }

}
