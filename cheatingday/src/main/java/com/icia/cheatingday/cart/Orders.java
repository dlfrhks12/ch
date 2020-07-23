package com.icia.cheatingday.cart;

import java.io.Serializable;
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
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int orderNo;
	private int menuno;
	private String uUsername;
	private String cartName;
	private int cartPrice;
	private LocalDateTime cartDay;
	private int cartCount;
	private String image;
	//private int sNum;
	private int cartJumunMoney;
}
