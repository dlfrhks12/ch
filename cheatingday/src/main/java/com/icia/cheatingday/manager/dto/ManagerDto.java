package com.icia.cheatingday.manager.dto;


import java.util.*;


import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.icia.cheatingday.user.dto.UserDto;

import lombok.Data;
import lombok.experimental.Accessors;

public class ManagerDto {
	
	private ManagerDto() {}
	
	@Data
	public static class DtoForJoin {
		@Pattern(regexp = "/^[0-9]{10}$/", message = "사업자번호는 10자리 숫자입니다")
		private int mNum;      //사업자 등록번호
		@Pattern(regexp = "^(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$", message = "비밀번호는 특수문자 포함 영숫자 8~10자입니다")
		private String mPassword; //사업자 비밀번호
		@Pattern(regexp = "^[A-Za-z][A-Za-z0-9]{8,10}$", message = "아이디는 영숫자 8~10자입니다")
		private String mUsername; //사업자 아이디
		@Email(message = "잘못된 이메일 형식입니다")
		private String mEmail;    //사업자 이메일
		@Pattern(regexp = "/^([0-9]{3})([0-9]{4})([0-9]{4})$/", message = "전화번호는 숫자 10~11자입니다")
		private String mTel;      //사업자 전화번호
		@Pattern(regexp = "^[가-힣]{2,5}$", message = "이름은 한글 2~5자입니다")
		private String mIrum;     //사업자 이름
		private String sName;
		private List<String> authorities;
	}
	
	@Data
	@Accessors(chain=true)
	public static class DtoForWrite{
		//원래테이블(storeapplyinsert)
		private int iNo; //입점신청번호
		private String mNum; //사업자등록번호
		//외부테이블(manager)
		private String mIrum; //사업자이름
		private String mTel; //사업자 전화번호
		private String mEmail; //사업자이메일
		private String mUsername; //사업자아이디
	}
	
	@Data
	@Accessors(chain=true)
	public static class DtoForRead{
		private String mUsername;
		private String mIrum;
		private int mNum;
		private String mEmail;
		private String mTel;
		private String mPassword;
	}
	@Data
	@Accessors(chain=true)
	public static class DtoForUpdate {
		private String mUsername;
		private String mIrum;
		private String mPassword;
		private String newMPassword;
		private String mTel;
		private String mEmail;
	}
}
