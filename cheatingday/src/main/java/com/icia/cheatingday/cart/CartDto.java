package com.icia.cheatingday.cart;

import java.util.List;

import com.icia.cheatingday.order.dto.DetailorderDto;
import com.icia.cheatingday.review.entity.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public class CartDto {
	private CartDto() {}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Accessors(chain=true)
	public static class DtoForRead{
	private Integer menuno;        //메뉴번호
	private int menusal;       //가격
	private String menusajin;  //사진
	private String menuname;   //이름
	private int sNum;       //음식점 고유번호
	private List<Review> reviewlist;
}
}
