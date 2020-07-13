package com.icia.cheatingday.cart;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductDto {
private ProductDto() { }
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class ProList implements Serializable {
		private Integer mNo;
		private String mName;
		private int mPrice;
		private int mCount;
		private String image;
	}
}
