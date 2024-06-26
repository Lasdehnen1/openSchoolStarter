package com.example.openschoolstarter.config;

import com.example.openschoolstarter.model.HttpExchangeLog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 *Interceptor for logging Http requests and responses
 * <p>
 *     Logging behaviour can be controlled via properties in {@link HttpLoggingProperties}
 * </p>
 */
@RequiredArgsConstructor
public class HttpLoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HttpLoggingInterceptor.class);

    private final HttpLoggingProperties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;

        HttpExchangeLog httpExchangeLog = new HttpExchangeLog();
        httpExchangeLog.setMethod(request.getMethod());
        httpExchangeLog.setUrl(request.getRequestURL().toString());
        httpExchangeLog.setStatusCode(response.getStatus());
        httpExchangeLog.setDuration_ms(duration);

        if (properties.isLogHeaders()) {
            httpExchangeLog.setRequest(getHeaders(request));
            httpExchangeLog.setResponse(getHeaders(response));
        }

        logger.info(httpExchangeLog.toString());
    }


    private Map<String,String> getHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headersNames = request.getHeaderNames();
        while (headersNames.hasMoreElements()) {
            String headerKey = headersNames.nextElement();
            String headerValue = request.getHeader(headerKey);
            headers.put(headerKey, headerValue);
        }
        return headers;
    }

    private Map<String, String> getHeaders(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();
        for(String header : response.getHeaderNames()) {
            String headerValue = response.getHeader(header);
            headers.put(header, headerValue);
        }
        return headers;
    }

}

