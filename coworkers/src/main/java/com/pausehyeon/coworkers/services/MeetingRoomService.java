package com.pausehyeon.coworkers.services;

import java.util.List;

import com.pausehyeon.coworkers.domain.MeetingRoom;
import com.pausehyeon.coworkers.exception.BusinessException;

public interface MeetingRoomService {
	public List<MeetingRoom> getMeetingRooms() throws BusinessException;
}
