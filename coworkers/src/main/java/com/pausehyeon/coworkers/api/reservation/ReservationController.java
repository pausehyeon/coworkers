package com.pausehyeon.coworkers.api.reservation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(path = "reservations", method = RequestMethod.GET)
	public Object getReservations() throws BusinessException {
		logger.debug("controller entered");
		service.makeReservation();
		return service.getReservations();
	}

}
