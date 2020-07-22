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
	
	//[관리자]블록유저카운트
	public int countblock() {
		return tpl.selectOne("adminMapper.countblock");
	}
	//[관리자] 계정 확인
	public String findById(String aUsername) {
		return tpl.selectOne("adminMapper.findById", aUsername);
	}
	//[관리자]전체 유저목록
	public List<User> findAllUser(){;
		return tpl.selectList("adminMapper.findAllUser");
	}
	//[관리자]유저 (선택)블랙하기
	public void blockAll(List<String> uUsernames) {
		tpl.update("adminMapper.blockAll", uUsernames);
	}
	//[관리자]블락된 유저목록
	public List<User> findAllBlock(){
		return tpl.selectList("adminMapper.findAllBlock");
	}
	//[관리자]유저 블락해제
	public void unblockAll(List<String> uUsernames) {
		tpl.update("adminMapper.unblockAll", uUsernames);
	}
	//[관리자]가입요청 사업자 목록
	public List<ManagerEntity> findAllByEnabled(){
		return tpl.selectList("adminMapper.findAllByEnabled");
	}
	//[관리자]가입승인
	public int enabledM(long mNum) {
		return tpl.update("adminMapper.enabledM", mNum);
	}
	//[관리자]비상용 관리자가입
	public int insert(Admin admin) {
		return tpl.insert("adminMapper.insert", admin);
	}
}
