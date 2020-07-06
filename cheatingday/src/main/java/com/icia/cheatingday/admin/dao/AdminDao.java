package com.icia.cheatingday.admin.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.admin.entity.*;
import com.icia.cheatingday.manager.entity.*;
import com.icia.cheatingday.user.entity.*;

@Repository
public class AdminDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public String findById(String aUsername) {
		return tpl.selectOne("adminMapper.findById", aUsername);
	}
	public List<User> findAllUser(){
		return tpl.selectList("adminMapper.findAllUser");
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
	public List<ManagerEntity> findAllByEnabled(){
		return tpl.selectList("adminMapper.findAllByEnabled");
	}
	public int enabledM(ManagerEntity manager) {
		return tpl.update("adminMapper.enabledM", manager);
	}
	public int insert(Admin admin) {
		return tpl.insert("adminMapper.insert", admin);
	}
}
