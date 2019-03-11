package com.pausehyeon.coworkers.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.pausehyeon.coworkers.api.responsecode.ResponseCode;
import com.pausehyeon.coworkers.api.responsecode.ResponseCodeService;
import com.pausehyeon.coworkers.exception.BusinessException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

	@Autowired
	ResponseCodeService responseService;
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponseBody> handleBusinessExcpetion(BusinessException e) {
		logger.debug("BUSINESS EXCEPTION OCCURRED: " + e.getStackTrace()[0].toString());
		//TODO 응답코드 매번 새로 읽지 않도록 수정하기
		ResponseCode response = responseService.getResponseDetail(e.getCode(), e.getParams());
		return ResponseEntity
				.status(response.getHttpCode())
				.body(ErrorResponseBody.builder().code(response.getCode()).message(response.getMessage()).build());
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponseBody> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		logger.debug("MethodArgumentTypeMismatchException OCCURRED: " + e.getStackTrace()[0].toString());
		return ResponseEntity
				.status(400)
				.body(ErrorResponseBody.builder().code("E002").message("입력 항목의 형식이 잘못되었습니다.").build());
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResponseBody> handleUnexpectedError(Throwable e) {
		logger.error("UNEXPECTED EXCEPTION OCCURRED: ", e);
		return ResponseEntity
				.status(500)
				.body(ErrorResponseBody.builder().code("E999").message("서버에서 오류가 발생했습니다. 담당자에게 문의해주세요.").build());
	}
}
