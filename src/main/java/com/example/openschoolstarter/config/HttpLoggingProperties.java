package com.example.openschoolstarter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Manages HTTP logging settings
 * @see HttpLoggingProperties enabled - enables logging
 * @see HttpLoggingProperties logHeaders - enables logging headers
 */
@ConfigurationProperties(prefix = "http.logging")
@Getter
@Setter
public class HttpLoggingProperties {
    private boolean enabled = true;
    private boolean logHeaders = false;
}