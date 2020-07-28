package com.icia.cheatingday.main.dto;

import lombok.*;
import lombok.experimental.*;

public class MainDto {
	private MainDto() {}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForList{
		private int sNum;
		private String sName;
		private String sSajin;
		private int foodNo;
		private String foodCategory;
		private Integer sReviewCnt;
		private Float sStarPoint;
	}
}

