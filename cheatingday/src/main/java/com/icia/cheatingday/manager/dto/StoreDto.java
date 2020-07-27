package com.icia.cheatingday.manager.dto;

import javax.validation.constraints.*;

import com.icia.cheatingday.main.dto.*;

import lombok.*;
import lombok.experimental.Accessors;

public class StoreDto {
	private StoreDto() {}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForRead{
	private String sSajin;
	private int sNum;
	private String sInfo;
	private String sName;
	private String sTel;
	private String sAddress;
	private int foodNo;
	private String foodCategory;
	private String mUsername;
	private Float sStarPoint;  
	private int sReviewCnt; 
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForUpdate {
		private String sInfo;
		private int sNum;
		private String sName;
		private String sTel;
		private String sAddress;
	}
}
