package com.icia.cheatingday.freeboard.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserBoardDao {
	@Select("select username from user_board where username=#{username} and bno = #{bno} and rownum = 1")
	public String alreadyExist(@Param("username")String username,@Param("bno")long bno);
	
	@Insert("insert into user_board values(#{username},#{bno})")
	public void insert(@Param("username")String username,@Param("bno")long bno);
}
