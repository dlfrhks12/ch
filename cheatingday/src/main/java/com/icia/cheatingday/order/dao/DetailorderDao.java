package com.icia.cheatingday.order.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.order.entity.*;

@Repository
public class DetailorderDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public int count() {
		return tpl.selectOne("detailorderMapper.count");
	}
	public List<DetailorderEntity> findAll(int startRowNum, int endRowNum) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("detailorderMapper.findAll", map);
	}
	public DetailorderEntity findById(Integer dNo) {
		return tpl.selectOne("detailorderMapper.findById", dNo);
	}
	public DetailorderEntity findByONo(int oNo) {
		return tpl.selectOne("detailorderMapper.findByONo", oNo);
	}
}
