package com.icia.cheatingday.allReview;

import java.time.LocalDateTime;

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
public class AllReview {
	private int rNo; 						// 리뷰번호
	private String rTitle;					// 제목
	private String uUsername;				// 작성자 아이디
	private Integer cateno;					// 카테고리
	private int rStarPoint;					// 별점
	private String rContent; 				// 내용
	private LocalDateTime rWriteTime;		// 작성시간
	private int oNo;						// 주문번호
	private int sNum;						// 음직점 고유버호
}
