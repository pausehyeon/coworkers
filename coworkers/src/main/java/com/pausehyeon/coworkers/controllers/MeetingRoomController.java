package com.pausehyeon.coworkers.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pausehyeon.coworkers.domain.MeetingRoom;
import com.pausehyeon.coworkers.services.MeetingRoomService;

@RestController
public class MeetingRoomController {
	private static final Logger logger = LoggerFactory.getLogger(MeetingRoomController.class);
	
	@Autowired
	MeetingRoomService service;
	
	@RequestMapping(path = "/meeting-rooms", method = RequestMethod.GET)
	public List<MeetingRoom> getMeetingRooms() {
		logger.debug("start");
		return service.getMeetingRooms();
	}
}
