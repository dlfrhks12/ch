package com.icia.cheatingday.center.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.icia.cheatingday.center.entity.*;

public interface QnACommentDao {
	//[관리자,사업자]댓글목록 출력
	@Select("select q_no qNo, qc_no qcNo, a_username aUsername, qc_content qcContent, qc_write_time qcWriteTime from qnacomment where q_no=#{qNo} order by qc_no desc")
	public List<QnAComment> findAllByQno(int qNo);

	//[관리자]댓글작성
	@Insert("insert into qnacomment values(qnacomment_seq.nextval, #{c.qNo}, #{c.aUsername}, #{c.qcContent}, sysdate)")
	public int insert(@Param("c") QnAComment qnacomment);

	//[관리자]댓글찾기
	@Select("select q_no qNo, qc_no qcNo, a_username aUsername, qc_content qcContent, qc_write_time qcWriteTime from qnacomment where qc_no=#{qcNo} and rownum=1")
	public QnAComment findById(Integer qcNo);

	//[관리자]댓글 업데이트
	@Update("update qnacomment set qc_content=#{qcContent} where qc_no=#{qcNo} and rownum=1")
	public int update(QnAComment qnAComment);
	
	//[관리자]댓글삭제
	@Delete("delete from qnacomment where qc_no=#{qcNo} and rownum=1")
	public int delete(Integer qcNo);
}
