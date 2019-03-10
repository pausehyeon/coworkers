package com.pausehyeon.coworkers.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pausehyeon.coworkers.domain.Reservation;
import com.pausehyeon.coworkers.exception.BusinessException;
import com.pausehyeon.coworkers.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{
	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	ReservationRepository repository;
	
	/**
	 * 
	 * @description 
	 * @return Reservation
	 */
	public Reservation makeReservation() throws BusinessException{
		Reservation reservation;
		
		/* DB Insert */
		reservation = repository.save(Reservation.builder()
				.mid(new Long(1))
				.title("통합회원제 회의")
				.userName("정지현")
				.pin("3942")
				.start(parseDate("201903141800"))
				.end(parseDate("201903141830"))
				.isRepeated(false)
				.firstRegisteredBy("jjh")
				.lastModifiedBy("jjh")
				.build());

		logger.debug("made reservation : {}", reservation.toString());
		
		return reservation;
	}
	
	/**
	 * 
	 * @description 
	 * @return List<Reservation>
	 */
	public List<Reservation> getReservations() throws BusinessException{
		return (List<Reservation>) repository.findAll();
	}
	
	private Date parseDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		try {
			return format.parse(str);
		} catch (ParseException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
