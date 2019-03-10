package com.pausehyeon.coworkers.services;

import java.util.List;

import com.pausehyeon.coworkers.domain.Reservation;
import com.pausehyeon.coworkers.exception.BusinessException;

public interface ReservationService {
	public Reservation makeReservation() throws BusinessException;
	public List<Reservation> getReservations() throws BusinessException;
}
