package com.pausehyeon.coworkers.api.reservation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pausehyeon.coworkers.exception.BusinessException;

@Service
public class ReservationServiceImpl implements ReservationService{
	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	ReservationRepository repository;
	
	@Override
	public Reservation getReservation(Long rid) throws BusinessException {
		Optional<Reservation> optionalReservation = repository.findById(rid);
		if(!optionalReservation.isPresent()) {
			throw new BusinessException("E101", "예약");
		}else {
			return optionalReservation.get();
		}
	}
	
	/**
	 * 
	 * @description 
	 * @return List<Reservation>
	 */
	@Override
	public List<Reservation> getReservations() throws BusinessException{
		return (List<Reservation>) repository.findAll();
	}

	/**
	 * 
	 * @description 
	 * @return Reservation
	 */
	@Override
	public Reservation postReservation(Reservation reservation) throws BusinessException{
		
		/* 입력값 체크 */
		//checkReservationInput(reservation);
		// TODO Spring Validator를 이용하여 구현하기
		
		/* 반복 예약 처리 */
		
		/* DB Insert */
		// 기본값 세팅
		reservation = repository.save(reservation);
	
		logger.debug("made reservation : {}", reservation.toString());
		
		return reservation;
	}
	
	@Override
	public Reservation putReservation(Reservation reservation) throws BusinessException {
		/* 입력값 체크 */
		
		/* 반복 예약 조회 */
		
		/* DB Update */
		return reservation;
	}
	
	/*
	private void checkReservationInput(Reservation reservation) throws BusinessException {
		if(StringUtils.isEmpty(reservation.getMid())) {
			throw new BusinessException("E001", "회의실ID");
		}
		
		if(StringUtils.isEmpty(reservation.getTitle())) {
			throw new BusinessException("E001", "예약명");
		}
		
		if(StringUtils.isEmpty(reservation.getUserName())) {
			throw new BusinessException("E001", "예약자명");
		}
		
		if(StringUtils.isEmpty(reservation.getPin())) {
			throw new BusinessException("E001", "예약PIN");
		}
		
		if(StringUtils.isEmpty(reservation.getStart())) {
			throw new BusinessException("E001", "예약시작일시");
		}
		
		if(StringUtils.isEmpty(reservation.getEnd())) {
			throw new BusinessException("E001", "예약종료일시");
		}
		
		if(reservation.getIsRepeated() == null) {
			throw new BusinessException("E001", "반복여부");
		}
		
		if(reservation.getIsRepeated() && StringUtils.isEmpty(reservation.getRepeatInterval())) {
			throw new BusinessException("E001", "반복간격");
		}
		
		if(reservation.getIsRepeated() && StringUtils.isEmpty(reservation.getRepeatCount())) {
			throw new BusinessException("E001", "반복횟수");
		}
	}
	*/
	
}
