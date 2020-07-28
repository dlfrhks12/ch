package com.icia.cheatingday.manager.dao;

import java.util.*;

import org.apache.ibatis.session.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.cheatingday.manager.entity.Store;

@Repository
public class StoreDao {

	@Autowired
	private SqlSessionTemplate tpl;


	/*//내 매장 리뷰갯수 읽기
	public int myReviewCnt(int sNum) {
		return tpl.selectOne("storeMapper.myReviewCnt",sNum);
	}*/

	
	//매장이 존재하는지 안하는지 확인
	public boolean existsSnum(String mUsername) {
		return tpl.selectOne("storeMapper.existsSnum",mUsername);
	}
	
	
	public Store findBysNum(int sNum) {
		return tpl.selectOne("storeMapper.findBysNum",sNum);
	}
	//findBymUsername으로 찾아야해 
	public Store findBymUsername(String mUsername) {
		return tpl.selectOne("storeMapper.findBymUsername", mUsername);
	}
	
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
	public float starAvg(int sNum) {
		return tpl.selectOne("storeMapper.starAvg",sNum);
	}
	public int count(Integer foodNo, String keyword) {
		Map<String,Object> map = new HashMap<>();
		map.put("foodNo", foodNo);
		map.put("keyword", keyword);
		return tpl.selectOne("storeMapper.count", map);
	}

	public List<Store> findAllByfoodNoAndStar(Integer foodNo, String keyword) {
		Map<String,Object> map = new HashMap<>();
		map.put("foodNo", foodNo);
		map.put("keyword", keyword);
		return tpl.selectList("storeMapper.findAllByfoodNoAndStar", map);
	}
	
	public List<Store> findAllByfoodNoAndReview(Integer foodNo, String keyword) {
		Map<String,Object> map = new HashMap<>();
		map.put("foodNo", foodNo);
		map.put("keyword", keyword);
		return tpl.selectList("storeMapper.findAllByfoodNoAndReview", map);
	}

	public List<Store> findAllByReview(String keyword) {
		Map<String,Object> map = new HashMap<>();
		map.put("keyword", keyword);
		return tpl.selectList("storeMapper.findAllByReview", map);
	}
	
	public List<Store> findAllByStar(String keyword) {
		Map<String,Object> map = new HashMap<>();
		map.put("keyword", keyword);
		return tpl.selectList("storeMapper.findAllByStar", map);
	}

}
