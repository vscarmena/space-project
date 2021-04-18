package com.vscarmena.space.service.config;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpTraceAcutuatorConfig {

    @Bean
    public HttpTraceRepository httpTraceRepository () {
        return new InMemoryHttpTraceRepository();
    }
}
