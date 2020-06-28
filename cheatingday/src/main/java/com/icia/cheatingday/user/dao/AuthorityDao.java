package com.icia.cheatingday.user.dao;

import org.apache.ibatis.annotations.*;

public interface AuthorityDao {
	// 권한
	@Insert("insert into authorities values(#{username}, #{authority})")
	void insert(@Param("username") String username, @Param("authority") String authority);
}
