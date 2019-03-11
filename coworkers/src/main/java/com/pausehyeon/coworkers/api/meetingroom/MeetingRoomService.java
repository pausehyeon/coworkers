package com.pausehyeon.coworkers.api.meetingroom;

import java.util.List;

import com.pausehyeon.coworkers.exception.BusinessException;

public interface MeetingRoomService {
	public List<MeetingRoom> getMeetingRooms() throws BusinessException;
}
