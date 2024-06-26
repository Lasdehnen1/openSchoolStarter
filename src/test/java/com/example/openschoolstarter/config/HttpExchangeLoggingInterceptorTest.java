package com.example.openschoolstarter.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpExchangeLoggingInterceptorTest {
    private HttpLoggingInterceptor interceptor;
    private HttpLoggingProperties properties;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        properties = new HttpLoggingProperties();
        properties.setEnabled(true);
        properties.setLogHeaders(true);
        interceptor = new HttpLoggingInterceptor(properties);
    }

    @Test
    public void preHandleTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = new Object();

        boolean result = interceptor.preHandle(request, response, handler);

        assertTrue(result);
        assertNotNull(request.getAttribute("startTime"));
    }

    @Test
    public void afterCompletionWithHeadersTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = new Object();

        request.setAttribute("startTime", System.currentTimeMillis());
        response.setStatus(200);
        request.addHeader("Test-Header", "Test-Value");
        response.setHeader("Response-Header", "Response-Value");

        // checking logs where request and response are not null
        interceptor.afterCompletion(request, response, handler, null);
    }
}
