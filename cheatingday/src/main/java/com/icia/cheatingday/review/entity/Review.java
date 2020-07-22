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
	private Integer rStarPoint;
	private LocalDateTime rWriteTime;
	private Integer oNo;
	private String rTitle;
	private Integer rReport;
	private String uUsername;
	private Integer sNum;
	
}
