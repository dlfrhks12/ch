package com.icia.cheatingday.user.dao;

import java.util.*;

import javax.inject.*;

import org.mybatis.spring.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.user.entity.*;

@Repository
public class PointDao {
	@Inject
	private SqlSessionTemplate tpl;
	
	public int count(String uUsername) {
		return tpl.selectOne("pointMapper.count", uUsername);
	}
	public List<Point> findAll(int startRowNum, int endRowNum, String uUsername) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		map.put("uUsername", uUsername);
		return tpl.selectList("pointMapper.findAll", map);
	}
	public int findByTotalpoint(String uUsername) {
		System.out.println(uUsername);
		System.out.println(uUsername);
		System.out.println(uUsername);
		return tpl.selectOne("pointMapper.findByTotalpoint", uUsername);
	}
	public int insert(Point point) {
		return tpl.insert("pointMapper.insert", point);
	}
	public int update(Point point) {
		return tpl.update("pointMapper.update", point);
	}
	public int delete(String uUsername) {
		return tpl.delete("pointMapper.delete", uUsername);
	}
	public int pointAdd(Point point) {
		return tpl.update("pointMapper.pointAdd", point);
	}
	public int pointMin(Point point) {
		return tpl.update("pointMapper.pointMin", point);
	}
	public int ordermoney(int orderNo) {
		return tpl.selectOne("pointMapper.ordermoney", orderNo);
	}
	
}
