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
	
	public int count() {
		return tpl.selectOne("pointMapper.count");
	}
	public List<Point> findAll(int startRowNum, int endRowNum) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("pointMapper.findAll", map);
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
}
