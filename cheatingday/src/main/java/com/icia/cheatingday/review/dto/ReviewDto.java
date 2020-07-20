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
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForRead{
		private Integer rNo;
		private String uUsername;
		private String rContent;
		private String rWriteTimeStr;
		private String rTitle;
		private Integer sNum;
		private String sName;
		private Integer rReport;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Accessors(chain=true)
	public static class DtoForWrite{
		private String rTitle;
		private String rContent;
		private String uUsername;
		private Integer sNum;
		private Integer sName;
	}
	
	@Data
	public static class DtoForUpdate{
		private Integer rNo;
		private String rTitle;
		private String rContent;
		private String uUsername;
	}
	
	
}
