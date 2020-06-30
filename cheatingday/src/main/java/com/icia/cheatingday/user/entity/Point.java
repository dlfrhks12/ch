package com.icia.cheatingday.user.entity;

import java.time.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class Point {
	private String uUsername;
	private String sName;
	private LocalDateTime accumulationDay;
	private String accumulationSal;
}