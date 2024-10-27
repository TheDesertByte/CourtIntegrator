package com.courtmanager.webapp.databases;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Component
public class MsAccessDb {

    private static MsAccessDb instance;
    private final JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private MsAccessDb() {
        this.jdbcTemplate = new JdbcTemplate(getDataSource());
    }

    public static synchronized MsAccessDb getInstance() {
        if (instance == null) {
            instance = new MsAccessDb();
        }
        return instance;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    private DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("net.ucanaccess.jdbc.UcanaccessDriver");
        return dataSource;
    }
}

