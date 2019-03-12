package com.pausehyeon.coworkers.api.reservation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PostReservationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Reservation.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Reservation reservation = (Reservation) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mid", "E011", new Object[] {"회의실ID"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "E011", new Object[] {"예약명"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "E011", new Object[] {"예약자명"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pin", "E011", new Object[] {"예약pin"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "start", "E011", new Object[] {"예약시작일시"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "end", "E011", new Object[] {"예약종료일시"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isRepeated", "E011", new Object[] {"반복여부"});
		
		if(reservation.getStart().equals(reservation.getEnd())) errors.rejectValue("end", "E013", new Object[]{"예약종료일시"}, "예약시작일시");
		if(reservation.getStart().after(reservation.getEnd())) errors.rejectValue("end", "E013", new Object[]{"예약종료일시"}, "예약시작일시");
		
//		errors.rejectValue("", "", new Object[]{""}, "");
	}
}
