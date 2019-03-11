package com.pausehyeon.coworkers.api.reservation;

import java.util.List;

import com.pausehyeon.coworkers.exception.BusinessException;

public interface ReservationService {
	public Reservation getReservation(Long rid) throws BusinessException;
	public List<Reservation> getReservations() throws BusinessException;
	public Reservation postReservation(Reservation reservation) throws BusinessException;
	public Reservation putReservation(Reservation reservation) throws BusinessException;
}
