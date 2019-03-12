package com.pausehyeon.coworkers.api.reservation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pausehyeon.coworkers.exception.BusinessException;

@Service
public class ReservationServiceImpl implements ReservationService{
	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	ReservationRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public Reservation getReservation(Long rid) throws BusinessException {
		Optional<Reservation> optionalReservation = repository.findById(rid);
		if(!optionalReservation.isPresent()) {
			throw new BusinessException("S012", "예약");
		}else {
			return optionalReservation.get();
		}
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Reservation> getReservations() throws BusinessException{
		return (List<Reservation>) repository.findAll();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Reservation postReservation(Reservation reservation) throws BusinessException{
		/* 반복 예약 처리 */
		
		/* DB Insert */
		// 기본값 세팅
		reservation = repository.save(reservation);
	
		logger.debug("made reservation : {}", reservation.toString());
		
		return reservation;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Reservation putReservation(Reservation reservation) throws BusinessException {
		/* 반복 예약 조회 */

		/* 반복 예약 처리 */
		
		/* DB Update */
		return reservation;
	}
	
}
