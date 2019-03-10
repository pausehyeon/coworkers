package com.pausehyeon.coworkers.domain;

import javax.persistence.Entity;
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
public class ResponseCode {
	@Id
	private String code;     // 응답코드
	@NonNull
	private String httpCode; // HTTP응답코드
	@NonNull
	private String message;  // 응답메세지
}
