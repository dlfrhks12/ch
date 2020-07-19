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
		private String category;
		private String rContent; 
		private int rStarPoint;
		private String rWriteTimeStr;
		private String rTitle;
		private String sName;
		private String menuname;
		private String sajin;
	}
}
