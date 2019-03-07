package com.pausehyeon.coworkers.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pausehyeon.coworkers.domain.Reservation;
import com.pausehyeon.coworkers.repository.ReservationRepository;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	ReservationRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Reservation> getReservations() {
		
		// TODO DB Connection Test (Temp)
		repository.save(Reservation.builder()
				.startDtti("201901010130")
				.endDtti("201901010200")
				.build());

		logger.debug("save completed");

		return (List<Reservation>) repository.findAll();
	}
}
