package com.icia.cheatingday.freeboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.icia.cheatingday.freeboard.entity.Attachment;

public interface AttachmentDao {
	@Insert("insert into attachment values(attachment_seq.nextval,#{a.writer},#{a.originalFileName},#{a.saveFileName},#{a.flength},#{a.bno},#{a.isImage})")
	public int insert(@Param("a")Attachment a);
	@Select("select f_no fno, f_writer writer,original_file_name originalFileName,save_file_name saveFileName,f_length flength,b_no bno,is_image isImage from attachment where b_no=#{bno}")
	public List<Attachment> findAllByBno(Integer bno);
	@Select("select f_no fno, f_writer writer,original_file_name originalFileName,save_file_name saveFileName,f_length flength,b_no bno,is_image isImage from attachment where f_no=#{fno} and rownum=1")
	public Attachment findById(Integer fno);
	@Delete("delete from attachment where f_no=#{fno} and rownum = 1")
	public int deleteById(Integer fno);
	@Delete("delete from attachment where b_no = #{bno} and rownum = 1")
	public int deleteAllByBno(Integer bno);//ㅁㄴㅇㅁㄴㅇㅁㄴㅇㄹㄹㄹ
}
