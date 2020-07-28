package com.icia.cheatingday.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class ManagerEntity { //사업자회원
	
	   private long mNum;      //사업자 등록번호
	   private String mPassword; //사업자 비밀번호
	   private String mUsername; //사업자 아이디
	   private String mEmail;    //사업자 이메일
	   private String mTel;      //사업자 전화번호
	   private String mAccount;  //사업자 전용계좌번호
	   private String mIrum;     //사업자 이름
	   private Boolean mEnabled;
	   private String status;
	   
}
