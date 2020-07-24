package com.icia.cheatingday.manager.dto;

import lombok.Data;
import lombok.experimental.Accessors;

public class MenuDto {

	private MenuDto() {}
	
	@Data
	@Accessors(chain=true)
	public static class DtoForRead{

		private int menuno;        //메뉴번호
		private int menusal;       //가격
		private String menusajin;  //사진
		private String menuname;   //이름
		private int sNum;
		//다른클래스
		private String mUsername;  //사업자이름
		private long mNum;       //사업자등록번호
		//다른클래스
		private String sName; //상호명
		
		private int mcount;
		
	}
	
}