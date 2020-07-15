package com.icia.cheatingday.user.entity;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class Point {
	private String uUsername;
	private int oNo;
	private int accumulationSal;
	private int favCheck;
}
