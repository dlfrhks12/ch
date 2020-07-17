package com.icia.cheatingday.allReview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AllReviewDao {
	@Inject
	private SqlSessionTemplate tpl;

	// 글 리스트 : 글 리스트, 인기글 리스트, 사용자로 검색
	public int count(Boolean isHit, String uUsername) {
		Map<String, Object> map = new HashMap<>();
		map.put("isHit", isHit);
		map.put("uUsername", uUsername);
		return tpl.selectOne("reviewMapper.count", map);
	}

	public List<AllReview> findAll(int startRowNum, int endRowNum) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("reviewMapper.findAll", map);
	}

	public List<AllReview> findAllByHit(int startRowNum, int endRowNum) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("reviewMapper.findAllByHit", map);
	}

	public List<AllReview> findAllByWriter(int startRowNum, int endRowNum, String uUsername) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		map.put("username", uUsername);
		return tpl.selectList("reviewMapper.findAllByWriter", map);
	}

	public AllReview findById(Integer rNo) {
		return tpl.selectOne("reviewMapper.findById", rNo);
	}

	public int insert(AllReview review) {
		return tpl.insert("reviewMapper.insert", review);
	}

	public int update(AllReview review) {
		return tpl.update("reviewMapper.update", review);
	}

	public int delete(int bno) {
		return tpl.delete("reviewMapper.delete", bno);
	}
}