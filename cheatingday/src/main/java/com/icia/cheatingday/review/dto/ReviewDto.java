package com.icia.cheatingday.review.dto;

import lombok.*;
import lombok.experimental.*;

public class ReviewDto {
	private ReviewDto() {}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForList {
		private int rNo; 
		private String rContent; 
		private int rStarPoint;
		private String rWriteTimeStr;
		private int oNo;
		private String rTitle;
		private int rReport;
		private String uUsername;
		private int sNum;
		private String sName;
	}
}
