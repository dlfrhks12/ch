package com.icia.cheatingday.buylist.entity;

import java.time.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Buylist {
	private int oNo;
	private Integer oTotal;
	private LocalDateTime oOrderTime;
	private String uUSername;
	private Integer oCheck;
	private int sNum;
}
