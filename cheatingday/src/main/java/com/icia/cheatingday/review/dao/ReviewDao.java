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
	
	//사업자&일반회원 리뷰신고
	public int reviewSingoUpdate(int rNo) {
		return tpl.update("reviewMapper.reviewSingo",rNo);
	}
	
	
	//사업자> 음식점 고유번호로 리뷰 리스트 페이징
	public int countBySnum(Integer sNum) {
		return tpl.selectOne("reviewMapper.countBySnum",sNum);
	}
	
	//사업자> 리뷰 페이징
	public List<Review> findAllBysNum(int startRowNum, int endRowNum, Integer sNum){
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		map.put("sNum", sNum);
		return tpl.selectList("reviewMapper.findAllBysNum",map);
	}
	
	//사업자> 음식점 리뷰 읽기
	public Review findByRno(int rNo) {
		return tpl.selectOne("reviewMapper.findByRno",rNo);
	}
	public int count() {
		return tpl.selectOne("reviewMapper.count");
	}
	public int countByRepoert() {	
		return tpl.selectOne("reviewMapper.countByRepoert");
	}
	public List<Review> findAllByReport(int startRowNum, int endRowNum){
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("reviewMapper.findAllByReport", map);
	}
	public Review findById(Integer rNo) {
		return tpl.selectOne("reviewMapper.findById", rNo);
	}
	public int delete(Integer rNo) {
		return tpl.delete("reviewMapper.delete", rNo);
	}
}
