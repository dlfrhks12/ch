package com.icia.cheatingday.center.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.center.entity.*;

@Repository
public class QnADao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	//[관리자,사업자]카테고리별 페이징용 카운트
	public int count(Integer qCano) {
		return tpl.selectOne("qnaMapper.count", qCano);
	}
	//[관리자,사업자]QNA불러오기
	public QnA findById(Integer qNo) {
		return tpl.selectOne("qnaMapper.findById", qNo);
	}
	//[관리자,사업자]QNA 전체리스트
	public List<QnA> findAll(int startRowNum, int endRowNum) {
		Map<String,Integer> map = new HashMap<>();
		map.put("startRowNum",
				
				
				
				
				
				
				
				
				
				
				
				
				startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("qnaMapper.findAll", map);
	}
	//[관리자,사업자]QNA 카테고리별리스트
	public List<QnA> findAllByqCano(int startRowNum, int endRowNum, Integer qCano){
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		map.put("qCano", qCano);
		return tpl.selectList("qnaMapper.findAllByqCano", map);
	}
	//[사업자]QNA 쓰기
	public int insert(QnA qnA) {
		return tpl.insert("qnaMapper.insert", qnA);
	}
	//[사업자]QNA 변경
	public int update(QnA qnA) {
		return tpl.update("qnaMapper.update", qnA);
	}
	//[사업자]QNA 삭제
	public int delete(Integer qNo) {
		return tpl.delete("qnaMapper.delete", qNo);
	}
}
