package com.icia.cheatingday.center.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.center.*;

@Repository
public class QnADao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public int count(String qUsername) {
		return tpl.selectOne("qnaMapper.count", qUsername);
	}
	public QnA findById(Integer qNo) {
		return tpl.selectOne("qnaMapper.findById", qNo);
	}
	public List<QnA> findAll(int startRowNum, int endRowNum){
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("qnaMapper.findAll", map);
	}
	public int insert(QnA qnA) {
		return tpl.insert("qnaMapper.insert", qnA);
	}
	public int update(QnA qnA) {
		return tpl.update("qnaMapper.update", qnA);
	}
	public int delete(Integer qNo) {
		return tpl.delete("qnaMapper.delete", qNo);
	}
}
