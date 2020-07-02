package com.icia.cheatingday.review;

import java.time.*;

import lombok.*;
import lombok.experimental.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class ReviewComment {
	private Integer rcNo;	// 리부답글번호
	private Integer	rNo;	// 리뷰 번호
	private String mNum;	// 사업자 등록번호
	private String rcContent;	// 답글 내용
	private LocalDateTime rcDateTime; // 답글시간
}
