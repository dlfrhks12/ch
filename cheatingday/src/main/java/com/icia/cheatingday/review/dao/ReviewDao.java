package com.icia.cheatingday.review.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.center.entity.*;
import com.icia.cheatingday.user.entity.*;

@Repository
public class ReviewDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public int count(Integer rReport) {
		return tpl.selectOne("reviewMapper.count", rReport);
	}
	public List<Review> findAllByReport(int startRowNum, int endRowNum, Integer rReport){
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		map.put("rReport", rReport);
		return tpl.selectList("reviewMapper.findAllByReport", map);
	}
	public Review findById(Integer rNo) {
		return tpl.selectOne("reviewMapper.findById", rNo);
	}
	public int delete(Integer rNo) {
		return tpl.delete("reviewMapper.delete", rNo);
	}
}
