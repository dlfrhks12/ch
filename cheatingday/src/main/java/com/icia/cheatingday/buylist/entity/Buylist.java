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
	private int orderNo;
	private int menuno;
	private String uUsername;
	private String cartName;
	private int cartPrice;
	private LocalDateTime cartDay;
	private int cartCount;
	private String image;
	private int cartJumunMoney;
	private int sNum;
}
