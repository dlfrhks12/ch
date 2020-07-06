package com.icia.cheatingday.authority.dao;

import org.apache.ibatis.annotations.*;

public interface AuthorityDao {
	@Insert("insert into authority values(#{username}, #{authority})")
	void insert(@Param("username") String username, @Param("authority") String authority);
	
	@Delete("delete from authority where username=#{username} and rownum=1")
	void delete(String username);
}
