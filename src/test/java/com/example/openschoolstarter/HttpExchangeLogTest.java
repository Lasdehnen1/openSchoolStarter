package com.example.openschoolstarter;

import com.example.openschoolstarter.model.HttpExchangeLog;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpExchangeLogTest {
    @Test
    public void httpLogTest() {
        HttpExchangeLog log = new HttpExchangeLog();
        log.setMethod("GET");
        log.setUrl("http://example.com");
        log.setStatusCode(200);
        log.setDuration_ms(123);

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Test-Header", "Test-Value");
        log.setRequest(requestHeaders);

        Map<String, String> responseHeaders = new HashMap<>();
        responseHeaders.put("Response-Header", "Response-Value");
        log.setResponse(responseHeaders);

        assertEquals("GET", log.getMethod());
        assertEquals("http://example.com", log.getUrl());
        assertEquals(200, log.getStatusCode());
        assertEquals(123, log.getDuration_ms());
        assertEquals(requestHeaders, log.getRequest());
        assertEquals(responseHeaders, log.getResponse());
    }
}