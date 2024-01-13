package com.hrushikesh.SpringBootLearning.Exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> hadleException(Exception ex, WebRequest request)
	{
		String errorMsg = ex.getLocalizedMessage();
		if(errorMsg == null)	
			errorMsg = ex.toString();
		
		ErrorMsgDesc emd = new ErrorMsgDesc(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), errorMsg);
		
		return createResponseEntity(emd, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(value = ResourseNotFoundException.class)
	public ResponseEntity<Object> resourceNotFound(ResourseNotFoundException ex, WebRequest request)
	{
		String errorMsg = ex.getLocalizedMessage();
		if(errorMsg == null)	
			errorMsg = ex.toString();
		
		ErrorMsgDesc emd = new ErrorMsgDesc(HttpStatus.NOT_FOUND.value(), new Date(), errorMsg);
		
		return createResponseEntity(emd, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
