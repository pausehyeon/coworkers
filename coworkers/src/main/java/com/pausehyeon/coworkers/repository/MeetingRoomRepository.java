package com.pausehyeon.coworkers.repository;

import org.springframework.data.repository.CrudRepository;

import com.pausehyeon.coworkers.domain.MeetingRoom;

public interface MeetingRoomRepository extends CrudRepository<MeetingRoom, Long> {

}
