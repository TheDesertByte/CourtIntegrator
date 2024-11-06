package com.courtmanager.webapp.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class MsAccessDb {

    @Resource
    public Environment env;

    public MsAccessDb() {
    }

    @PostConstruct
    public Connection test() throws SQLException {
        try {
            String url = "jdbc:ucanaccess://C:/Users/CG411MW/projects/Courts.mdb;openExclusive=false;ignoreCase=true;showSchema=true";
            // String url2 = env.getProperty("spring.datasource.url");
            // String driver2 = env.getProperty("spring.datasource.driver-class-name");
            // DriverManagerDataSource dataSource = new DriverManagerDataSource();
            // dataSource.setUrl(url2);
            // dataSource.setDriverClassName(driver2);
            // return dataSource.getConnection();
            return DriverManager.getConnection(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException(ex.getMessage().toString());
        }
    }
}
