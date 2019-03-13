package com.pausehyeon.coworkers.api.reservation;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	@Query("SELECT r FROM Reservation r "
			+ "WHERE r.end > (:date) "
			+ "AND r.end < (:nextDay)")
	public List<Reservation> findAllByDate(@Param("date") Date date, @Param("nextDay") Date nextDay);
	
	@Query("SELECT count(1) FROM Reservation r "
			+ "WHERE mid = (:mid) "
			+ "AND ((r.start <= (:start) AND r.end > (:start))"
			+ "OR (r.start > (:start) AND r.start < (:end))) ")
	public Long countDuplicatedReservations(@Param("start") Date Start, @Param("end") Date End, @Param("mid") Long mid);
	
	public List<Reservation> findByRepresentativeRid(Long representativeRid);
}
