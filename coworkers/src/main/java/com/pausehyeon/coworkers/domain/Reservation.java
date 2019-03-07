package com.pausehyeon.coworkers.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder()
@Getter
@ToString
@EqualsAndHashCode
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long rsvId;
	@NonNull
	private String startDtti;
	@NonNull
	private String endDtti;
	
}
