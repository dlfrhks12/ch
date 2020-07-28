package com.icia.cheatingday.manager.dto;


import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public class ManagerDto {
	
	private ManagerDto() {}
	
	@Data
	@Accessors(chain=true)	//주문리스트
public static class DtoForOrderList{
	
		
		//기존 orders 테이블
		private int orderNo; //주문번호
		private String cartDayStr; //주문시간
		private String uUsername; //사용자아이디
		private int sNum; //상호번호
		private int cartPrice;//메뉴금액
		private int cartCount;//메뉴수량
		private String cartName; //메뉴이름
		private int menuno; //메뉴번호
	
	}
	
	@Data
	@Accessors(chain=true)	//주문읽기
	public static class DtoForOrderRead{
		
		//기존 detailorder 테이블
		private int dSal; //메뉴금액
		private int dMenuCnt; //메뉴수량
		private String dMenuName; //메뉴이름
		private int orderNo; //주문번호
		private int menuno; //메뉴번호
		private String uUsername; //사용자아이디
	}
	
	
	
	@Data
	@Accessors(chain=true)
	public static class DtoForReviewRead{
		private int rNo; 
		private String rContent; 
		private int rStarPoint;
		private String rWriteTimeStr;
		private int orderNo;
		private String rTitle;
		private int rReport;
		private String uUsername;
		
	}
	
	
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Accessors(chain=true)
	@Builder
	public static class DtoForList {
		private int rNo;  //리뷰번호
		private int rStarPoint; //별점
		private String rWriteTimeStr; //작성시간
		private String rTitle; //제목
		private String uUsername; //일반회원아이디
		private int sNum; //음식점고유번호
	}
	
	
	@Data
	public static class DtoForJoin {
		@Pattern(regexp = "/^[0-9]{10}$/", message = "사업자번호는 10자리 숫자입니다")
		private long mNum;      //사업자 등록번호
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
		private List<String> authorities;
	}
	
	@Data
	@Accessors(chain=true)
	public static class DtoForWrite{
		//원래테이블(storeapplyinsert)
		private int iNo; //입점신청번호
		private long mNum; //사업자등록번호
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
		private long mNum;
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
