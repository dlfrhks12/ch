package com.icia.cheatingday.center.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.icia.cheatingday.center.entity.*;

public interface QnACategoryDao {
	//카테고리 리스트
	@Select("select q_cano qcano, q_category qcategory from qnacategory")
	public List<QnACategory> findAll();
	//카테고리 이름 출력
	@Select("select q_category qCategory from qnacategory where q_cano=#{qCano} and rownum=1")
	public String findById(Integer qCano);	
}
