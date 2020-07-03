package com.icia.cheatingday.authority.dao;

import org.apache.ibatis.annotations.*;

public interface AuthorityDao {
	@Insert("insert into authoruty values(#{username}, #{authority})")
	void insert(@Param("username") String username, @Param("authority") String authority);
}
