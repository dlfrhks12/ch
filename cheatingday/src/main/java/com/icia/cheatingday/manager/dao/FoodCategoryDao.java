package com.icia.cheatingday.manager.dao;

import org.apache.ibatis.annotations.Select;

import com.icia.cheatingday.manager.entity.FoodCategory;

public interface FoodCategoryDao {

	@Select("select food_no foodNo, food_category foodCategory from foodcategory where food_no=#{foodNo}")
	public FoodCategory findByFoodNo(int foodNo);
}

