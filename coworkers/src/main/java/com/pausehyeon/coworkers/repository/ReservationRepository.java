package com.pausehyeon.coworkers.repository;

import org.springframework.data.repository.CrudRepository;

import com.pausehyeon.coworkers.domain.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
