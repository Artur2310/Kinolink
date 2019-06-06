package ru.kinolink.web.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

public class TestDBConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource basicDataSource = new DriverManagerDataSource();
        basicDataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("");
        basicDataSource.setUrl("jdbc:hsqldb:mem:bmmemdb");
        return basicDataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

}
