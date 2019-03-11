package com.pausehyeon.coworkers.api.responsecode;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ResponseCode {
	@Id
	private String code;     // 응답코드
	@NonNull
	private Integer httpCode; // HTTP응답코드
	@Setter
	@NonNull
	private String message;  // 응답메세지
}
