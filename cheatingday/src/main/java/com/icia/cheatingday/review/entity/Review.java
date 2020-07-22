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
	private int rNo; 
	private String rContent; 
	private int rStarPoint;
	private LocalDateTime rWriteTime;
	private int oNo;
	private int rReport;
	private String uUsername;
	private int sNum;
}
