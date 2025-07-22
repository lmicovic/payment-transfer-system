package com.example.payment_transfer_system.configuration.interceptors;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.payment_transfer_system.configuration.interceptors.request_wrapper.CachedBodyHttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TransferInterceptor implements HandlerInterceptor {
	
//	private static final Logger loggerAccount = LoggerFactory.getLogger("account");
//	private static final Logger loggerAccountError = LoggerFactory.getLogger("account_error");
//	private static final Logger loggerTransaction = LoggerFactory.getLogger("transactions");
//	private static final Logger loggerTransactionError = LoggerFactory.getLogger("transactions_error");

	private static final Logger loggerTransaction = LoggerFactory.getLogger("transactions");
	private static final Logger loggerTransactionError = LoggerFactory.getLogger("transactions_error");
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	// Called before Controller Method is envoked
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	// Called after Controller Method is envoked
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	// Called after complete Request is completed
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
		if (request instanceof CachedBodyHttpServletRequest cachedRequest) {
            
			Exception handledException = (Exception) request.getAttribute("exception");					// Here are stored All Exceptions that are Handled in GlobalExceptionHandler.
			
			String requestBody = cachedRequest.getCachedBodyAsString();
            
			Integer sourceAccountId = null;
			Integer destinationAccountId = null;
			BigDecimal amount = null;
			try {
				
                Map<String, Object> bodyMap = objectMapper.readValue(requestBody, Map.class);
                
                // Get Attributes from Request
                sourceAccountId = new Integer(bodyMap.get("sourceAccountId").toString());
                destinationAccountId = new Integer(bodyMap.get("destinationAccountId").toString());
                amount = new BigDecimal(bodyMap.get("amount").toString());

            } catch (Exception e) {
                e.printStackTrace();
                HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
                return;
            }            	
			
			// If Exception occred Log Eror in File
            if(handledException != null) {
            	loggerTransactionError.error("Transaction[sourceAccountId: " + sourceAccountId + ", destinationAccountId: " + destinationAccountId + ", amount: " + amount + "] - " + handledException.getMessage());
			}
            // If exception did not occured, log successful transaction in File.
            else {
            	loggerTransaction.info("Transaction[sourceAccountId: " + sourceAccountId + ", destinationAccountId: " + destinationAccountId + ", amount: " + amount + "] - SUCCESSFUL");
            }
            
        }
		
		
		
//		
		
	
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	// preHandle() -> Controller -> postHandle() -> View Rendered -> afterCompletion()
	
	// postHandle() vs afterCompletion():
	// postHandle(): 			called after Controller Method is finished, but before View is Rendered. PostHandle() can access Model and View. (Used for: Modify Response, Add Atributes to the Model).
	// afterCompletion():		called after Controller, after complete Request Execution is finished and View is Rendered. Can not access Model and View. (User for: Clean-up Task, Logging, Resource Releasing, Error Handling).	
	
	
}
