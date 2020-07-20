package com.icia.cheatingday.review.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.cheatingday.review.entity.ReviewComment;

@Repository
public class ReviewCommentDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public ReviewComment findById(Integer rcNo) {
		return tpl.selectOne("reviewCommentMapper.findById",rcNo);
	}
	public List<ReviewComment> findAllByRno(Integer rno){
		return tpl.selectList("reviewCommentMapper.findAllByRno",rno);
	}
	public int insert(ReviewComment reviewComment) {
		return tpl.insert("reviewCommentMapper.insert",reviewComment);
	}
	public int update(ReviewComment reviewComment) {
		return tpl.update("reviewCommentMapper.update",reviewComment);
	}
	public int delete(Integer rcNo) {
		return tpl.delete("reviewCommentMapper.delete",rcNo);
	}
	
}
