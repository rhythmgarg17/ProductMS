package com.infosys.Product.Utility;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import com.infosys.Product.Exception.ProductException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@Autowired
	 Environment environment;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception, ServletWebRequest request) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimestamp(LocalDateTime.now());
		error.setPathUri(request.getDescription(true));
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(ProductException exception, ServletWebRequest request) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(environment.getProperty(exception.getMessage()));
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setPathUri(request.getDescription(true));
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(MethodArgumentNotValidException exception, HttpHeaders headers, ServletWebRequest request) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setPathUri(request.getDescription(true));
		String errorMsg = exception.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(", "));
		errorInfo.setErrorMessage(errorMsg);
		errorInfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> pathExceptionHandler(ConstraintViolationException exception, ServletWebRequest request) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setPathUri(request.getDescription(true));
		String errorMsg = exception.getConstraintViolations().stream().map(x -> x.getMessage()).collect(Collectors.joining(", "));
		errorInfo.setErrorMessage(errorMsg);
		errorInfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}

}
	
