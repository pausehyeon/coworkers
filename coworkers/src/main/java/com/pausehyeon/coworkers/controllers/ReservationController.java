package com.pausehyeon.coworkers.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pausehyeon.coworkers.domain.Reservation;
import com.pausehyeon.coworkers.services.ReservationService;

@RestController
public class ReservationController {
	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	ReservationService service;
	
	@RequestMapping(path = "reservations", method = RequestMethod.GET)
	public List<Reservation> getReservations() {

		logger.debug("controller entered");
		service.makeReservation();
		
		return service.getReservations();
	}

}
