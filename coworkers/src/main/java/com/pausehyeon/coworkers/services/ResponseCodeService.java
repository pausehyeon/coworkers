package com.pausehyeon.coworkers.services;

import com.pausehyeon.coworkers.domain.ResponseCode;

public interface ResponseCodeService {
	public ResponseCode getResponseDetail(String code, Object[] params);
}
