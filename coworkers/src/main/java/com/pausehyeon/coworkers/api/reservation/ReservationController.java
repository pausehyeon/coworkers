package com.pausehyeon.coworkers.api.reservation;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pausehyeon.coworkers.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReservationController {
	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	private final PostReservationValidator postReservationValidator;
	
	@Autowired
	private final PutReservationValidator putReservationValidator;
	
	@Autowired
	ReservationService service;

	@GetMapping("/reservations")
	public Object getReservations() throws BusinessException {
		logger.debug("start");
		return service.getReservations();
	}
	
	@GetMapping("/reservations/{date}")
	public Object getReservations(@PathVariable Date date) throws BusinessException {
		logger.debug("date="+date.toString());
		return service.getReservations(date);
	}

	@GetMapping("/reservation/{rid}")
	public Object getReservation(@PathVariable Long rid) throws BusinessException {
		logger.debug("start");
		return service.getReservation(rid);
	}
	
	@PostMapping("/reservation")
	public Object postReservation(@RequestBody Reservation reservation, BindingResult bindingResult) throws BusinessException {
		// 입력값 유효성 체크
		postReservationValidator.validate(reservation, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BusinessException(bindingResult.getFieldError().getCode(), bindingResult.getFieldError().getArguments());
		}
		return service.postReservation(reservation);
	}
	
	@PutMapping("/reservation")
	public Object putReservation(@RequestBody Reservation reservation, BindingResult bindingResult) throws BusinessException {
		// 입력값 유효성 체크
		putReservationValidator.validate(reservation, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BusinessException(bindingResult.getFieldError().getCode(), bindingResult.getFieldError().getArguments());
		}
		return service.postReservation(reservation);
	}

}
