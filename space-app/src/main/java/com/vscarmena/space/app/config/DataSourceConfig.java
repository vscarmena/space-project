package com.vscarmena.space.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig{

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    private String url;
    private String username;
    private String password;

    @Bean
    public void localDataSourceConfig() {
        LOGGER.info("-------------- DATASOURCE CONFIGURATI0N ---------------------");
        LOGGER.info("url: {}", url);
        LOGGER.info("username: {}", username);
        LOGGER.info("password: {}", password);
        LOGGER.info("--------------------------------------------------------------");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
