package com.icia.cheatingday.center.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.center.entity.*;

@Repository
public class QnACommentDao {
	@Autowired
	private SqlSessionTemplate tpl;

	public List<QnAComment> findAllByqNo(){
		return tpl.selectList("qnacomMapper.findAll");
	}
	public int insert(QnAComment qnAComment) {
		return tpl.insert("qnacomMapper.insert", qnAComment);
	}
	public QnAComment findById(Integer qcNo) {
		return tpl.selectOne("qnacomMapper.findById", qcNo);
	} 
	public int update(QnAComment qnAComment) {
		return tpl.update("qnacomMapper.update", qnAComment);
	}
	public int delete(Integer qcNo) {
		return tpl.delete("qnacomMapper.delete", qcNo);
	}
}
