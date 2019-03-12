package com.pausehyeon.coworkers.advice;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.pausehyeon.coworkers.exception.BusinessException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

	private final MessageSource messageSource;

	public ControllerAdvice(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponseBody> handleBusinessExcpetion(BusinessException e, Locale locale) {
		logger.debug("BUSINESS EXCEPTION OCCURRED: " + e.getStackTrace()[0].toString());
		return getResponseEntity(locale, e.getCode(), e.getParams());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponseBody> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, Locale locale) {
		logger.debug("MethodArgumentTypeMismatchException OCCURRED: " + e.getStackTrace()[0].toString());
		return getResponseEntity(locale, "E002");
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponseBody> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, Locale locale) {
		logger.debug("HttpMessageNotReadableException OCCURRED: " + e.getStackTrace()[0].toString());
		return getResponseEntity(locale, "E002");
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResponseBody> handleUnexpectedError(Throwable e, Locale locale) {
		logger.error("UNEXPECTED EXCEPTION OCCURRED: ", e);
		return getResponseEntity(locale, "E999");
	}
	
	private ResponseEntity<ErrorResponseBody> getResponseEntity(Locale locale, String code, Object... params){
		return ResponseEntity
				.status(Integer.parseInt(messageSource
						.getMessage("response.status."+code, null, locale)))
				.body(ErrorResponseBody
						.builder()
						.code(code)
						.message(messageSource.getMessage("response.msg."+code, params, locale))
						.build());
	}
}
