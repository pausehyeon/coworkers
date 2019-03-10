package com.pausehyeon.coworkers.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class MeetingRoom {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long mid;                 // 회의실ID
	@NonNull
	private String name;              // 회의실명
	@NonNull
	private String buildingName;      // 건물명
	@NonNull
 	private Integer floor;            // 층수
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date usableFrom;          // 가용시작일자
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date usableTo;            // 가용종료일자
	@CreationTimestamp
	private Date firstRegisteredAt;   // 최초등록일시
	@NonNull
	private String firstRegisteredBy; // 최초등록사용자
	@UpdateTimestamp
	private Date lastModifiedAt;      // 최종변경일시
	@NonNull
	private String lastModifiedBy;    // 최종변경사용자
}