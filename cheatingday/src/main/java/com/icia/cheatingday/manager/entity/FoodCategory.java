package com.icia.cheatingday.manager.entity;


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
public class FoodCategory {

	private Integer foodNo; //음식점 카테고리번호
	private String foodCategory;
}
