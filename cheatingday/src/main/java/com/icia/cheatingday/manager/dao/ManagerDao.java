package com.icia.cheatingday.manager.dao;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.cheatingday.manager.entity.ManagerEntity;

@Repository
public class ManagerDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public Boolean existsByManagerNumber(long mNum) {
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
	
	public ManagerEntity findById(@NotNull String mUsername) {
		return tpl.selectOne("managerMapper.findById", mUsername);
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
	
	public int delete(long mNum) {
		return tpl.selectOne("managerMapper.delete", mNum);
	}

	//사업자아이디로 탈퇴하기
	public int deleteByMusername(String mUsername) {
		return tpl.delete("managerMapper.deleteByMusername", mUsername);
	}
	
	//사업자등록번호로 사업자아이디 읽어오기
	public String findMusernameByMnum(long mNum) {
		return tpl.selectOne("managerMapper.findMusernameByMnum", mNum);
	}
	public String findMirumeByMnum(long mNum) {
		return tpl.selectOne("managerMapper.findMirumeByMnum", mNum);
	}

}
