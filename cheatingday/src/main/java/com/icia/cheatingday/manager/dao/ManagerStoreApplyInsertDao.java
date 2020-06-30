package com.icia.cheatingday.manager.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.icia.cheatingday.manager.dto.ManagerDto.DtoForWrite;
import com.icia.cheatingday.manager.entity.ManagerEntity;
import com.icia.cheatingday.manager.entity.StoreApplyInsert;

public interface ManagerStoreApplyInsertDao {

	@Insert("insert into storeapplyinsert values(storeapplyinsert_seq.nextval, #{i.mNum})")
	public int insert(@Param("i") DtoForWrite storeapplyInsert);
	//iNo: 입점신청번호, mNum:사업자등록번호


	
	@Select("select m_irum mIrum from manager where m_num=#{mNum} and rownum=1")
	public String findByIrum(String mNum);
	//m_irum: 사업자이름
	
	@Select("select m_tel mTel from manager where m_num=#{mNum} and rownum=1")
	public String findByTel(String mNum);
	//m_tel: 사업자 전화번호
	
	@Select("select m_email mEmail from manager where m_num=#{mNum} and rownum=1")
	public String findByEmail(String mNum);
}
	//m_email: 사업자 이메일


