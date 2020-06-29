package com.icia.cheatingday.user.dto;

import java.time.*;
import java.util.*;

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;

public class UserDto {
	private UserDto() {}
	@Data
	public static class DtoForJoin {
		// 알파벳으로 시작하는 영숫자 8~10자
		@Pattern(regexp="^[A-Za-z][A-Za-z0-9]{7,9}$", message="아이디는 영숫자 8~10자입니다" )
		private String uUsername;
		@Pattern(regexp="(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$", message="비밀번호는 특수문자를 포함하는 영숫자 8~10자입니다")
		private String uPassword;
		@Pattern(regexp="^[가-힣]{2,5}$", message="이름은 한글 2~5자입니다")
		private String uIrum;
		@Email(message="잘못된 이메일 형식입니다")
		private String uEmail;
		@NotNull(message="전화번호는 필수입력입니다")
		private String uTel;
		@NotNull(message="생일은 필수입력입니다")
		private LocalDateTime birthDate;
		private List<String> authorities;
	}
	
	@Data
	@Accessors(chain=true)
	public static class DtoForRead {
		private String uUsername;
		private String uIrum;
		private String uEmail;
		private String uTel;
		private String uAddress;
		private String uPassword;
		private String joinDateStr;
		private long days;
		private int uPoint;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class DtoForList {
		private int bno;
		private String uUsername;
		private String sName;
		private String accumulationDayStr;
		private String accumulationSal;
	}
	
	@Data
	@Accessors(chain=true)
	public static class DtoForUpdate {
		@Pattern(regexp="^[A-Za-z][A-Za-z0-9]{7,9}$", message="아이디는 영숫자 8~10자입니다" )
		private String uUsername;
		@Pattern(regexp="^[가-힣]{2,5}$", message="이름은 한글 2~5자입니다")
		private String uIrum;
		@Pattern(regexp="(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$", message="비밀번호는 특수문자를 포함하는 영숫자 8~10자입니다")
		private String uPassword;
		@Pattern(regexp="(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$", message="비밀번호는 특수문자를 포함하는 영숫자 8~10자입니다")
		private String newUPassword;
		@NotNull(message="전화번호는 필수입력입니다")
		private String uTel;
		@NotNull
		@Email(message="잘못된 이메일 형식입니다")
		private String uEmail;
	}
	
}
