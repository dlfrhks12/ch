package com.icia.cheatingday.center.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.icia.cheatingday.center.*;


public interface QnACommentDao {

	@Select("select q_no, qc_no, a_username, qc_content, qc_write_time qcWriteTime from qnacomment where q_no=#{qNo} order by qc_no desc")
	public List<QnAComment> findAllByQNo(int qNo);

	@Insert("insert into qnacomment values(qnacomment_seq.nextval, #{q.qNo}, #{q.aUsername}, #{q.qcContent}, sysdate)")
	public void insert(@Param("q")QnAComment qnAComment);

	@Select("select q_no, qc_no, a_username, qc_content, qc_write_time qcWriteTime from qnacomment where qc_no=#{qcNo} and rownum=1")
	public QnAComment findById(Integer qcNo);
	
	@Delete("delete from qnacomment where qc_no=#{qcNo} and rownum=1")
	public void delete(Integer qcNo);
	

}
