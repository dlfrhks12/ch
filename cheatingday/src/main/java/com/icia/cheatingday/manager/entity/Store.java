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
public class Store {

	private int sNum;     //음식점 고유번호  시퀀스주기 . 인트로.
	private String sSajin;   //매장사진
	private String sInfo;    //매장정보
	private String sName;    //상호명
	private String sTel;     //음식점 전화번호
	private String sAddress; //주소
	private int foodNo;     //음식 카테고리 번호
	private Float sStarPoint;  //별점평균
	private Integer sReviewCnt; //리뷰개수
	private String mUsername; //사업자아이디
}