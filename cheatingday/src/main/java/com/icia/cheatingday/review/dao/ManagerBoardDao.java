package com.icia.cheatingday.review.dao;

import org.apache.ibatis.annotations.*;

public interface ManagerBoardDao {
	@Select("select username from manager_board where username=#{mUsername} and r_no=#{rNo} and rownum=1")
	public String alreadyExist(@Param("mUsername") String mUsername, @Param("rNo") int rNo);

	@Insert("insert into manager_board values(#{mUsername}, #{rNo})")
	public void insert(@Param("mUsername") String mUsername, @Param("rNo") int rNo);

}
