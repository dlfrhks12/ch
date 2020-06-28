package com.icia.cheatingday.admin.dao;


import org.apache.ibatis.annotations.*;


public interface AdminDao {

	@Select("select a_irum aIrum from admin where a_username=#{aUsername} and rownum=1")
	public String findById(String aUsername);
	
}
