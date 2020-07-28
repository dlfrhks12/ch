package com.icia.cheatingday.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.icia.cheatingday.review.entity.Review;
import com.icia.cheatingday.review.entity.ReviewComment;

public interface ReviewCommentDao {
	@Select("select r_no rNo,rc_no rcNo,rc_content rcContent,m_num mNum,m_username mUsername, rc_date_time rcDateTime from reviewcomment where r_no=#{rNo} order by rc_no desc")
	public List<ReviewComment> findByRno(Integer rNo);
	
	@Insert("insert into reviewcomment values(reviewcomment_seq.nextval,#{r.rNo},#{r.mNum},#{r.rcContent},sysdate, #{r.mUsername})")
	public int insert(@Param("r") ReviewComment reviewComment);
	
	@Select("select r_no rNo,rc_no rcNo,rc_content rcContent,m_num mNum,m_username mUsername, rc_date_time rcDateTime from reviewcomment where rc_no=#{rcNo} and rownum = 1")
	public ReviewComment findByRcno(Integer rcNo);
	
	@Delete("delete from reviewcomment where rc_no=#{rcNo} and rownum = 1")
	public int delete(Integer rcNo);
}
