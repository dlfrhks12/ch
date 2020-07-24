package com.icia.cheatingday.review.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.icia.cheatingday.review.entity.Review;
import com.icia.cheatingday.review.entity.ReviewComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public class ReviewDto {
	private ReviewDto() {}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForList {
		private Integer rNo; 
		private Integer sNum;
		private String category;
		private String rContent; 
		private Integer rStarPoint;
		private String rWriteTimeStr;
		private Integer orderNo;
		private String rTitle;
		private Integer rReport;
		private String uUsername;
		private String sName;
		private String menuname;
		private String sajin;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForReviewList{
		private Integer rNo;
		private Integer sNum;
		private String sName;
		private Integer rStarPoint;
		private String rContent;
		private String rTitle;
		private String rWriteTimeStr;
		private String uUsername;
		
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
		private Integer rStarPoint;
		private List<ReviewComment> comments;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Accessors(chain=true)
	public static class DtoForWrite{
		private String rTitle;
		private LocalDateTime rWriteTime;
		private String rContent;
		private String uUsername;
		private Integer orderNo;
		private Integer rStarPoint;
	}
	
	@Data
	public static class DtoForUpdate{
		private Integer rNo;
		private String rTitle;
		private String rContent;
		private String uUsername;
		private Integer rStarPoint;
	}
	
	
}
