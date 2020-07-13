package com.icia.cheatingday.manager.dao;

import org.apache.ibatis.annotations.*;

public interface FoodCategoryDao {

	@Select("select food_category foodCategory from foodcategory where food_no=#{foodNo}")
	public String findByFoodNo(int foodNo);
}

