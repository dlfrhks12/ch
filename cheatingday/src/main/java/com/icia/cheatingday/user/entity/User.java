package com.icia.cheatingday.user.entity;

import java.time.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class User {
	private String uUsername;			// 아이디
	private String uIrum;				// 이름
	private String uEmail;				// 이메일
	private String uTel;				// 전화번호
	private String uAddress;			// 주소
	private String uPassword;			// 비밀번호
	private Boolean uEnabled;			// 활성화 여부
	private LocalDateTime uJoinDate;	// 가입일자
	private int uPoint;					// 포인트
	private int uLoginFailCnt;			// 로그인 실패 횟수
	private String status;				// 로그인 성공 확인
}
