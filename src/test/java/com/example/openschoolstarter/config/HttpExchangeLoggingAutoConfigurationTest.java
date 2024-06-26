package com.example.openschoolstarter.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpExchangeLoggingAutoConfigurationTest {
    private final WebApplicationContextRunner contextRunner = new WebApplicationContextRunner().
            withConfiguration(AutoConfigurations.of(HttpLoggingAutoConfiguration.class));

    @Test
    void autoConfigurationEnabledTest() {
        contextRunner.withPropertyValues("http.logging.enabled=true").run(context -> assertThat(context).hasSingleBean(HttpLoggingInterceptor.class));
    }

    @Test
    void autoConfigurationDisabledTest() {
        contextRunner.withPropertyValues("http.logging.enabled=false").run(context -> assertThat(context).doesNotHaveBean(HttpLoggingInterceptor.class));
    }

    @Configuration
    static class configTest {
        @Bean
        public HttpLoggingProperties httpLoggingProperties() {
            HttpLoggingProperties properties = new HttpLoggingProperties();
            properties.setEnabled(true);
            properties.setLogHeaders(true);
            return properties;
        }
    }
}