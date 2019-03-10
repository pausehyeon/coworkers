package com.pausehyeon.coworkers.services;

import java.util.List;

import com.pausehyeon.coworkers.domain.Reservation;

public interface ReservationService {
	public Reservation makeReservation();
	public List<Reservation> getReservations();
}
