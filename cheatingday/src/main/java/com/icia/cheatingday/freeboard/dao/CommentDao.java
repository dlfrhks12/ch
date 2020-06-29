package com.icia.cheatingday.freeboard.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.cheatingday.freeboard.entity.Comment;

@Repository
public class CommentDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	
	public Comment findById(Integer cno) {
		return tpl.selectOne("commentMapper.findById",cno);
	}
	public List<Comment> findAllByBno(Integer bno){
		return tpl.selectList("commentMapper.findAllByBno",bno);
	}
	public int insert(Comment comment) {
		return tpl.insert("commentMapper.insert",comment);
	}
	public int update(Comment comment) {
		return tpl.update("commentMapper.update",comment);
	}
	public int delete(int cno) {
		return tpl.delete("commentMapper.delete",cno);
	}
}
