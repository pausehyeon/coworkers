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
//		Reservation reservation = (Reservation) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mid", "E001", new Object[] {"회의실ID"});
	}

}
