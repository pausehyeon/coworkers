package com.pausehyeon.coworkers.api.reservation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pausehyeon.coworkers.api.responsecode.ResponseCodeService;
import com.pausehyeon.coworkers.exception.BusinessException;

@RestController
public class ReservationController {
	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	ReservationService service;

	@Autowired
	ResponseCodeService responseService;

	@GetMapping("/reservations")
	public Object getReservations() throws BusinessException {
		logger.debug("start");
		return service.getReservations();
	}

	@GetMapping("/reservation/{rid}")
	public Object getReservation(@PathVariable Long rid) throws BusinessException {
		logger.debug("start");
		return service.getReservation(rid);
	}
	
	@PostMapping("/reservation")
	public Object postReservations(@RequestBody Reservation reservation) throws BusinessException {
		logger.debug("start");
		return service.postReservation(reservation);
	}

}
