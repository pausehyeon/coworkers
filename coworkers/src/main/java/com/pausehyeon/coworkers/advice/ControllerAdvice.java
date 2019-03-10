package com.pausehyeon.coworkers.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pausehyeon.coworkers.domain.ResponseCode;
import com.pausehyeon.coworkers.exception.BusinessException;
import com.pausehyeon.coworkers.services.ResponseCodeService;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

	@Autowired
	ResponseCodeService responseService;
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponseBody> handleBusinessExcpetion(BusinessException e) {
		logger.debug("BUSINESS EXCEPTION OCCURRED: " + e.getStackTrace()[0].toString());
		ResponseCode response = responseService.getResponseDetail(e.getCode(), e.getParams());
		return ResponseEntity
				.status(response.getHttpCode())
				.body(ErrorResponseBody.builder().code(response.getCode()).message(response.getMessage()).build());
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResponseBody> handleUnexpectedError(Throwable e) {
		logger.error("UNEXPECTED EXCEPTION OCCURRED: ", e);
		return ResponseEntity
				.status(500)
				.body(ErrorResponseBody.builder().code("E999").message("서버에서 오류가 발생했습니다. 담당자에게 문의해주세요.").build());
	}
}
