package com.icia.cheatingday.allReview;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public class AllReviewDto {
	private AllReviewDto() { }
	
	@Data
	@Accessors(chain=true)
	public static class DtoForuUsername {
		@NotBlank
		@Pattern(regexp="^[\\w\\s가-힣!]{1,50}$", message ="제목은 영숫자와 한글, 특수문자 !만 사용할 수 있습니다")
		private String rTitle;					// 제목
		private int rStarPoint;					// 별점
		private String rContent; 				// 내용
		private LocalDateTime rWriteTime;		// 작성시간
		private int oNo;						// 주문번호
		private String uUsername;				// 작성자 아이디
		private int sNum;						// 음직점 고유버호
	}
	
	@Data
	@Accessors(chain=true)
	public static class DtoForRead {
		private int rNo; 						// 리뷰번호
		private String rTitle;					// 제목
		private int rStarPoint;					// 별점
		private String rContent; 				// 내용
		private LocalDateTime rWriteTime;		// 작성시간
		private int oNo;						// 주문번호
		private String uUsername;				// 작성자 아이디
		private int sNum;						// 음직점 고유버호
		private List<ReviewComment> ReviewCommnet;
	}
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class DtoForList {
		private int rNo; 						// 리뷰번호
		private String rTitle;					// 제목
		private int rStarPoint;					// 별점
		private LocalDateTime rWriteTime;		// 작성시간
		private String uUsername;				// 작성자 아이디
		private int sNum;						// 음직점 고유버호
	}
	
	@Data
	public static class DtoForUpdate {
		@NotBlank
		@Pattern(regexp="^[\\w\\s가-힣!]{1,50}$", message ="제목은 영숫자와 한글, 특수문자 !만 사용할 수 있습니다")
		private String rTitle;					// 제목
		private int rStarPoint;					// 별점
		private String rContent; 				// 내용
		private String uUsername;				// 작성자 아이디
	}
}
