package com.icia.cheatingday.user.entity;

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
	private String rStarPoint;
	private LocalDateTime rWriteTime;
	private int oNo;
	private String rTitle;
	private int rReport;
	private String uUsername;
	private int sNum;
	private int rcNo;
}
