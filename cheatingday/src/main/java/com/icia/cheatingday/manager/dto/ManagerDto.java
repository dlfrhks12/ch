package com.icia.cheatingday.manager.dto;


import javax.validation.constraints.*;

import lombok.*;

public class ManagerDto {
	@Data
	public static class DtoForJoin {
		@Pattern(regexp = "/[^0-9]{0,10}/g", message = "사업자 등록번호는 숫자 10자리입니다")
		private String mNum;      //사업자 등록번호
		@Pattern(regexp = "^(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$", message = "비밀번호는 특수문자 포함 영숫자 8~10자입니다")
		private String mPassword; //사업자 비밀번호
		@Pattern(regexp = "^[A-Za-z][A-Za-z0-9]{8,10}$", message = "아이디는 영숫자 8~10자입니다")
		private String mUsername; //사업자 아이디
		//@pattern(regexp = "/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i" message = "잘못된 이메일 형식입니다")
		@Email(message = "잘못된 이메일 형식입니다")
		private String mEmail;    //사업자 이메일
		@Pattern(regexp = "[^0-9]{10,11}", message = "전화번호는 숫자 10~11자입니다")
		private String mTel;      //사업자 전화번호
		@Pattern(regexp = "^[가-힣]{2,5}$", message = "이름은 한글 2~5자입니다")
		private String mIrum;     //사업자 이름
	}
	
}
