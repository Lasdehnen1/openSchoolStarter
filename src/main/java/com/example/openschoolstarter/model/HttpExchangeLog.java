package com.example.openschoolstarter.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class HttpExchangeLog {
    private String method;
    private String url;
    private int statusCode;
    private long duration_ms;
    private Map<String, String> request;
    private Map<String, String> response;

}
