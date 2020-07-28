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
	
	//페이징카운트
	public int count() {
		return tpl.selectOne("noticeMapper.count");
	}
	//[전체]공지목록
	public List<Notice> findAll(int startRowNum, int endRowNum){
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("noticeMapper.findAll", map);
	}
	//[전체]공지불러오기
		public Notice findById(Integer nNo) {
			return tpl.selectOne("noticeMapper.findById", nNo);
		}
	//[관리자]공지입력
	public int insert(Notice notice) {
		return tpl.insert("noticeMapper.insert", notice);
	}
	//[관리자]공지변경
	public int update(Notice notice) {
		return tpl.update("noticeMapper.update", notice);
	}
	//[관리자]공지삭제
	public int delete(Integer nNo) {
		return tpl.delete("noticeMapper.delete", nNo);
	}
}
