package com.example.payment_transfer_system.configuration.interceptors.request_wrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Used to wrap every HTTP Request before it reaches the interceptor or controller.<br>
 * Spring Boot processes the HTTP request in a pipeline:<br>
 * <b>Client → Filters → Interceptors → Controller</b><br><br>
 * 
 * By default, HttpServletRequest.getInputStream() or getReader() can be read only once. If the interceptor tries to read the body directly, it will consume it, and the controller will fail to read it again (e.g. JSON won't be deserialized into an object).<br><br>
 * 
 * What RequestBodyCachingFilter Does:<br>
 * It replaces the original HttpServletRequest with your custom CachedBodyHttpServletRequest, which Caches the request body in memory, and Allows multiple reads of the body safely. 
 * 
 * 
 * 
 */
@Component
public class RequestBodyCachingFilter implements Filter {

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();

        // Skip H2 console - Request Filter
        if (path.startsWith("/h2-console")) {
            chain.doFilter(request, response);
            return;
        }

        CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(httpRequest);
        chain.doFilter(wrappedRequest, response);
    }
}
