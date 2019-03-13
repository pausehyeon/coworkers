
package com.pausehyeon.coworkers.api.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PutReservationValidator implements Validator {

	@Autowired
	private final PostReservationValidator postReservationValidator;
	
	public PutReservationValidator(PostReservationValidator postReservationValidator) {
		this.postReservationValidator = postReservationValidator;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Reservation.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Reservation reservation = (Reservation) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rid", "E011", new Object[] {"예약ID"});
		
		ValidationUtils.invokeValidator(postReservationValidator, reservation, errors);
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastModifiedThrough", "E011", new Object[] {"최종변경채널"});		
	}
}
