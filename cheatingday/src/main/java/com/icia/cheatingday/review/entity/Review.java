package com.icia.cheatingday.review.entity;

import java.time.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true) 
public class Review {
	private Integer rNo; 
	private String rContent; 
	private String rTitle;
	private Integer rStarPoint;
	private LocalDateTime rWriteTime;
	private int rReport;
	private Integer orderNo;
	private String uUsername;
	private Integer sNum;
	
}
