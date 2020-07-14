package com.icia.cheatingday.manager.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ReviewCheckDao {

	@Select("select username from manager_board where username=#{username} and r_no=#{rNo} and rownum=1")
	public String alreadyExist(@Param("username") String username, @Param("rNo")int rNo);
	
	@Insert("insert into manager_board values(#{username}, #{rNo})")
	public void insert(@Param("username") String username, @Param("rNo")int rNo);
	
}
