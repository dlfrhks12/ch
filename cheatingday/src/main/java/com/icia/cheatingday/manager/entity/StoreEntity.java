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
public class StoreEntity {

	private String sNum;     //음식점 고유번호
	private String sSajin;   //매장사진
	private String sInfo;    //매장정보
	private String sName;    //상호명
	private String sTel;     //음식점 전화번호
	private String sAddress; //주소
	private int foodNo;      //음식 카테고리 번호
	
}