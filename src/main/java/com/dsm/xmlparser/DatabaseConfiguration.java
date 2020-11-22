package com.dsm.xmlparser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class DatabaseConfiguration {

    private static final String DRIVER = "<DRIVER>";
    private static final String URL = "<URL>";
    private static final String USERNAME = "<USERNAME>";
    private static final String PASSWORD = "<PASSWORD>";

    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url(URL)
                .driverClassName(DRIVER)
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }
}
