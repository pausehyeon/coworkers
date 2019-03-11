package com.pausehyeon.coworkers.api.reservation;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pausehyeon.coworkers.api.meetingroom.MeetingRoom;

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
	private Long rid;                 // 예약ID
	@NonNull
	private Long mid;                 // 회의실ID
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="mid", insertable=false, updatable=false)
	private MeetingRoom meetingRoom;  // 회의실
	@NonNull
	private String title;             // 예약명
	@NonNull
	private String userName;          // 예약자명
	@NonNull
	private String pin;               // 예약pin
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;               // 예약시작일시
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;                 // 예약종료일시
	@NonNull
	private Boolean isRepeated;       // 반복여부
	private Integer repeatInterval;   // 반복간격
	private Integer repeatCount;      // 반복횟수
	private Long representative_rid;  // 대표예약번호
	@CreationTimestamp
	private Date firstRegisteredAt;   // 최초등록일시
	@NonNull
	private String firstRegisteredBy; // 최초등록사용자
	@UpdateTimestamp
	private Date lastModifiedAt;      // 최종변경일시
	@NonNull
	private String lastModifiedBy;    // 최종변경사용자
}
