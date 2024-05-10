package org.example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("org.example")
public class SpringConfig {
    @Bean
    public DriverManagerDataSource dataSource(){
        var dataSources=new DriverManagerDataSource("jdbc:postgresql://localhost:5432/book8mod4home",
        "postgres", "root123");
        dataSources.setDriverClassName("org.postgresql.Driver");
        return dataSources;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){

        return new JdbcTemplate(dataSource());
    }

}
