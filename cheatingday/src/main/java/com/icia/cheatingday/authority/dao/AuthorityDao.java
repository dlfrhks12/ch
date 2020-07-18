package com.icia.cheatingday.authority.dao;

import org.apache.ibatis.annotations.*;

public interface AuthorityDao {
	//권한 부여
	@Insert("insert into authority values(#{username}, #{authority})")
	void insert(@Param("username") String username, @Param("authority") String authority);
	
	//유저 삭제시 권한도 삭제
	@Delete("delete from authority where username=#{username} and rownum=1")
	void delete(String username);
	
}
