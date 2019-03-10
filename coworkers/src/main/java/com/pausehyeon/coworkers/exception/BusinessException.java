package com.pausehyeon.coworkers.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
	private static final long serialVersionUID = 4573645062719192100L;
	
	String code;
	Object[] params;

	public BusinessException(String code, Object... params) {
		super();
		this.code = code;
		this.params = params;
	}
}
