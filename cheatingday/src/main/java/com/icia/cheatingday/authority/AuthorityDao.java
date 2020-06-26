package com.icia.cheatingday.authority;

import org.apache.ibatis.annotations.*;

public interface AuthorityDao {
	@Insert("insert into authority values(#{username}, #{authority})")
	void insert(@Param("username") String username, @Param("authority") String authority);
}
