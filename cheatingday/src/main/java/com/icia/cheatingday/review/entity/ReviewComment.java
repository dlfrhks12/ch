package com.icia.cheatingday.review.entity;

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
public class ReviewComment {
	private Integer rcNo;
	private Integer rNo;
	private Long mNum;
	private String rcContent;
	private LocalDateTime rcDateTime;
	private String mUsername;
}
