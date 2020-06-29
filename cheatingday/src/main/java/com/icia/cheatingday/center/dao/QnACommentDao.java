package com.icia.cheatingday.center.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.icia.cheatingday.center.entity.*;

public interface QnACommentDao {
	@Select("select q_no qNo, qc_no qcNo, a_username aUsername, qc_content qcContent, qc_write_time qcWriteTime from qnacomment where q_no=#{qNo} order by qc_no desc")
	public List<QnAComment> findAllByQno(Integer qNo);

	@Insert("insert into qnacomment values(qnacomment_seq.nextval, #{c.qNo}, #{c.aUsername}, #{c.qcContent}, sysdate)")
	public int insert(@Param("c") QnAComment qnacomment);

	@Select("select q_no qNo, qc_no qcNo, a_username aUsername, qc_content qcContent, qc_write_time qcWriteTime from qnacomment where qc_no=#{qcNo} and rownum=1")
	public QnAComment findById(Integer qcNo);

	@Update("update qnacomment set qc_content=#{qcContent} where qc_no=#{qcNo} and rownum=1")
	public int update(QnAComment qnAComment);
	
	@Delete("delete from qnacomment where qc_no=#{qcNo} and rownum=1")
	public int delete(Integer qcNo);
}
