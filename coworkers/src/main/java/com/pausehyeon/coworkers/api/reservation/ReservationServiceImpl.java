package com.pausehyeon.coworkers.api.reservation;

import java.time.ZoneId;
import java.util.Date;
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
public class ReservationServiceImpl implements ReservationService {
	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	ReservationRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Reservation getReservation(Long rid) throws BusinessException {
		Optional<Reservation> optionalReservation = repository.findById(rid);
		if (!optionalReservation.isPresent()) {
			throw new BusinessException("S012", "예약");
		} else {
			return optionalReservation.get();
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Reservation> getReservations() throws BusinessException {
		return (List<Reservation>) repository.findAll();
	}
	
	@Override
	public List<Reservation> getReservations(Date date) throws BusinessException {
		logger.debug("date+1="+plusDays(date, 1).toString());
		return (List<Reservation>) repository.findAllByDate(date, plusDays(date, 1));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Reservation postReservation(Reservation reservation) throws BusinessException {
		/* 기본값 세팅 */
		reservation.setLastModifiedThrough(reservation.getFirstRegisteredThrough()); // 최종변경일시

		/* 중복체크 후 등록 */
		reservation = saveReservation(reservation);
		logger.debug("made reservation : {}", reservation.toString());

		/* 반복예약 등록 */
		if (reservation.getIsRepeated()) {
			saveRepeatedReservations(reservation);
		}

		return reservation;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Reservation putReservation(Reservation reservation) throws BusinessException {
		/* 업데이트 전 조회 */
		Optional<Reservation> asIsReservation = repository.findById(reservation.getRid());
		if(!asIsReservation.isPresent()) {
			throw new BusinessException("E203");
		}

		/* 중복체크 후 업데이트 */
		reservation = saveReservation(reservation);
		logger.debug("made reservation : {}", reservation.toString());

		/* 기존 반복 예약 조회 */
		List<Reservation> asIsReservationList = null;
		
		if(asIsReservation.get().getIsRepeated()) {
			asIsReservationList = repository.findByRepresentativeRid(reservation.getRepresentativeRid());
			
			/* 기존 반복 예약 삭제 */
			repository.deleteAll(asIsReservationList);
		}
		
		/* 반복예약 등록 */
		if (reservation.getIsRepeated()) {
			saveRepeatedReservations(reservation);
		}

		return reservation;
	}

	/**
	 * 
	 * @description 중복 체크 후 등록 혹은 업데이트 
	 * @param reservation
	 * @return
	 * @throws BusinessException
	 * @return Reservation
	 */
	private Reservation saveReservation(Reservation reservation) throws BusinessException {
		if(repository.countDuplicatedReservations(reservation.getStart(), reservation.getEnd(), reservation.getMid()) > 0) {
			throw new BusinessException("E202");
		}else {
			reservation = repository.save(reservation);
			return reservation;
		}
	}
	
	/**
	 * 
	 * @description 반복 예약 등록
	 * @param reservation
	 * @throws BusinessException
	 * @return void
	 */
	private void saveRepeatedReservations(Reservation reservation) throws BusinessException {
		/* 반복 예약 DB Insert */
		Reservation repeatedReservation = new Reservation();
		for (int i = 1; i < reservation.getRepeatCount(); i++) {
			repeatedReservation = Reservation.builder()
					.mid(reservation.getMid())
					.title(reservation.getTitle())
					.userName(reservation.getUserName())
					.start(plusDays(reservation.getStart(), reservation.getRepeatInterval() * i))
					.end(plusDays(reservation.getEnd(), reservation.getRepeatInterval() * i))
					.isRepeated(reservation.getIsRepeated())
					.repeatInterval(reservation.getRepeatInterval())
					.repeatCount(reservation.getRepeatCount())
					.representativeRid(reservation.getRid())
					.firstRegisteredThrough(reservation.getFirstRegisteredThrough())
					.lastModifiedThrough(reservation.getLastModifiedThrough())
					.build();

			repeatedReservation = saveReservation(repeatedReservation);
			logger.debug("made reservation " + i + " = " + repeatedReservation.toString());
		}
	}

	private Date plusDays(Date date, int days) {
		return Date.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
				.plusDays(days).atZone(ZoneId.systemDefault()).toInstant());
	}
}
