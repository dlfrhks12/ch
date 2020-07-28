package com.icia.cheatingday.buylist.dao;

import java.util.*;

import javax.inject.*;

import org.mybatis.spring.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.buylist.entity.*;

@Repository
public class BuylistDao { 
	@Inject
	private SqlSessionTemplate tpl;
	
	public Buylist findById(String uUsername) {
		return tpl.selectOne("buylistMapper.findById", uUsername);
	}
	public Buylist findByOrderNo(int orderNo) {
		return tpl.selectOne("buylistMapper.findByOrderNo", orderNo);
	}

	public int count(String uUsername) {
		return tpl.selectOne("buylistMapper.count", uUsername);
	}
	public List<Buylist> findAll(int startRowNum, int endRowNum, String uUsername) {
		Map<String,Object> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		map.put("uUsername", uUsername);
		return tpl.selectList("buylistMapper.findAll", map);
	}
}
