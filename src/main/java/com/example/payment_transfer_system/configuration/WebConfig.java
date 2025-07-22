package com.example.payment_transfer_system.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.payment_transfer_system.configuration.interceptors.TransferInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	//--------------------------------------------------
	// Register Interceptors:
	//--------------------------------------------------
	// Register my custom Interceptors.
	//--------------------------------------------------
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TransferInterceptor()).addPathPatterns("/transfer/**");
	}
	
}
