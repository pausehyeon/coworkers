package com.pausehyeon.coworkers.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pausehyeon.coworkers.domain.ResponseCode;
import com.pausehyeon.coworkers.repository.ResponseCodeRepository;

@Service
public class ResponseCodeServiceImpl implements ResponseCodeService {
	@Autowired
	ResponseCodeRepository repository;
	
	@Override
	public ResponseCode getResponseDetail(String code, Object[] params) {
		Optional<ResponseCode> responseCode;
		String message;
		
		responseCode = repository.findById(code);
		if(responseCode.isPresent()) {
			if(params != null) {
				message = String.format(responseCode.get().getMessage(), params);
				responseCode.get().setMessage(message);
			}
			
			return responseCode.get();
		}else {
			return new ResponseCode("E999", 500, "서버에서 오류가 발생했습니다. 담당자에게 문의해주세요.");
		}
	}

}
