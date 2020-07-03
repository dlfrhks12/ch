package com.icia.cheatingday.manager.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.manager.entity.*;

@Repository
public class ManagerDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public Boolean existsByManagerNumber(int mNum) {
		return tpl.selectOne("managerMapper.existsByManagerNumber", mNum);
	}
	
	public boolean existsById(String mUsername) {
		return tpl.selectOne("managerMapper.existsById", mUsername);
	}
	
	public boolean existsByEmail(String mEmail) {
		return tpl.selectOne("managerMapper.existsByEmail", mEmail);
	}
	
	public int insert(ManagerEntity manager) {
		return tpl.insert("managerMapper.insert", manager);
	}
	
	public ManagerEntity findById(int mNum) {
		return tpl.selectOne("managerMapper.findById", mNum);
	}
	
	public String findUsernameByIrumAndEmail(String mIrum, String mEmail) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("mIrum", mIrum);
		map.put("mEmail", mEmail);
		return tpl.selectOne("managerMapper.findUsernameByIrumAndEmail", map);
	}
	
	public int update(ManagerEntity manager) {
		return tpl.update("managerMapper.update", manager);
	}
	
	public int delete(int mNum) {
		return tpl.selectOne("managerMapper.delete", mNum);
	}
	
}
