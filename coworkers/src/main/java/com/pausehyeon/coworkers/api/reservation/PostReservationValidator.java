package com.pausehyeon.coworkers.api.reservation;

import java.time.ZoneId;
import java.util.Date;

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
		if(reservation.getTitle().length() > 10) errors.rejectValue("title", "E014", new Object[]{"예약명", 10}, "");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "E011", new Object[] {"예약자명"});
		if(reservation.getUserName().length() > 10) errors.rejectValue("userName", "E014", new Object[]{"예약자명", 10}, "");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "start", "E011", new Object[] {"예약시작일시"});
		if(!(getMinute(reservation.getStart()) == 0 || getMinute(reservation.getStart()) == 30)) errors.rejectValue("start", "E201", new Object[]{"예약시작일시"}, "");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "end", "E011", new Object[] {"예약종료일시"});
		if(!(getMinute(reservation.getEnd()) == 0 || getMinute(reservation.getEnd()) == 30)) errors.rejectValue("end", "E201", new Object[]{"예약종료일시"}, "");
		
		if(!reservation.getStart().before(reservation.getEnd())) errors.rejectValue("end", "E013", new Object[]{"예약시작일시", "예약종료일시"}, "");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isRepeated", "E011", new Object[] {"반복여부"});
		if(reservation.getIsRepeated()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatInterval", "E011", new Object[] {"반복간격"});
			if(reservation.getRepeatInterval() > 99) errors.rejectValue("repeatInterval", "E013", new Object[]{"반복간격", 99}, "");
			if(reservation.getRepeatInterval() < 1) errors.rejectValue("repeatInterval", "E015", new Object[]{"반복간격", 1}, "");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatCount", "E011", new Object[] {"반복횟수"});
			if(reservation.getRepeatCount() > 99) errors.rejectValue("repeatCount", "E013", new Object[]{"반복횟수", 99}, "");
			if(reservation.getRepeatCount() < 1) errors.rejectValue("repeatCount", "E015", new Object[]{"반복횟수", 1}, "");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstRegisteredThrough", "E011", new Object[] {"최초등록채널"});
	}
	
	private int getMinute(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().getMinute();
	}
}
