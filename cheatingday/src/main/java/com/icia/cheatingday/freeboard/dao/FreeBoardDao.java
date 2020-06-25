package com.icia.cheatingday.freeboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.icia.cheatingday.freeboard.entity.FreeBoard;


@Repository
public class FreeBoardDao {
	@Inject
	private SqlSessionTemplate tpl;
//목록 불러오기, 글 읽기  쓰기 수정 삭제
	public List<FreeBoard> findAll(int startRowNum, int endRowNum) {
		Map<String,Integer> map = new HashMap<>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("freeBoardMapper.findAll", map);
	}
	public FreeBoard findById(int bno) {
		return tpl.selectOne("freeBoardMapper.findById",bno);
	}
	public int insert(FreeBoard board) {
		return tpl.insert("freeBoardMapper.insert",board);
	}
	public int update(FreeBoard board) {
		return tpl.update("freeBoardMapper.update",board);
	}
	public int delete(int bno) {
		return tpl.delete("freeBoardMapper.delete",bno);
	}
	
}
