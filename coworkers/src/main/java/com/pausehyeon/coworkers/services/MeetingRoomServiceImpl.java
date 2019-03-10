package com.pausehyeon.coworkers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pausehyeon.coworkers.domain.MeetingRoom;
import com.pausehyeon.coworkers.exception.BusinessException;
import com.pausehyeon.coworkers.repository.MeetingRoomRepository;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {
	
	@Autowired
	MeetingRoomRepository repository;
	
	public List<MeetingRoom> getMeetingRooms() throws BusinessException{
		if(repository.count() < 0) {
			throw new BusinessException("E101", "회의실");
		}
		
		return (List<MeetingRoom>) repository.findAll();
	}
}
