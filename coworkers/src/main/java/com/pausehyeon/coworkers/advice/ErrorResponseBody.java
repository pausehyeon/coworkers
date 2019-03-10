package com.pausehyeon.coworkers.advice;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponseBody {
	private String code;
	private String message;
}
