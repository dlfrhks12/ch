package com.icia.cheatingday.notice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.icia.cheatingday.notice.entity.*;

@Repository
public class NoticeDao {
	@Inject
	private SqlSessionTemplate tpl;
	
	public int count() {
		return tpl.selectOne("noticeMapper.count");
	}
	public Notice findById(Integer nNo) {
		return tpl.selectOne("noticeMapper.findById", nNo);
	}
	public List<Notice> findAll(int startRowNum, int endRowNum){
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("noticeMapper.findAll", map);
	}
	public int insert(Notice notice) {
		return tpl.insert("noticeMapper.insert", notice);
	}
	public int update(Notice notice) {
		return tpl.update("noticeMapper.update", notice);
	}
	public int delete(Integer nNo) {
		return tpl.delete("noticeMapper.delete", nNo);
	}
}
