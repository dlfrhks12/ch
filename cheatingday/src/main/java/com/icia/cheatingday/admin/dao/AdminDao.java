package com.icia.cheatingday.admin.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.user.entity.*;

@Repository
public class AdminDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public String findById(String aUsername) {
		return tpl.selectOne("adminMapper.findById", aUsername);
	}
	public void blockAll(List<String> uUsernames) {
		tpl.update("adminMapper.blockAll", uUsernames);
	}
	public List<User> findAllBlock(){
		return tpl.selectList("adminMapper.findAllBlock");
	}
	public void unblockAll(List<String> uUsernames) {
		tpl.update("adminMapper.unblockAll", uUsernames);
	}
}
