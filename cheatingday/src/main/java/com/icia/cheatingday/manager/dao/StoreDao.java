package com.icia.cheatingday.manager.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.cheatingday.manager.entity.Store;

@Repository
public class StoreDao {

	@Autowired
	private SqlSessionTemplate tpl;
	
	public Store findBysNum(int sNum) {
		return tpl.selectOne("storeMapper.findBysNum",sNum);
	}
	
	//findBymUsername으로 찾아야해 
	public Store findBymUsername(String mUsername) {
		return tpl.selectOne("storeMapper.findBymUsername", mUsername);
	}
	
	/*
	 * public List<Store> findAll() { return tpl.selectList("storeMapper.findAll");
	 * }
	 */
	
	//사업자아이디로 리스트 출력하기
	public List<Store> findAllBymUsername(String mUsername){
		return tpl.selectList("storeMapper.findAllBymUsername",mUsername);
	}
	
	public int insert(Store store) {
		return tpl.insert("storeMapper.insert",store);
	}
	
	public int update(Store store) {
		return tpl.update("storeMapper.update",store);
	}
	
	public int delete(int sNum) {
		return tpl.delete("storeMapper.delete",sNum);
	}
}
