package com.icia.cheatingday.manager.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.cheatingday.manager.entity.MenuEntity;

@Repository
public class MenuDao {

	@Autowired
	private SqlSessionTemplate tpl;
	
	//메뉴리스트
	public List<MenuEntity> findAll(){
		return tpl.selectList("menuMapper.findAll");
	}
	
	//mUsername으로 메뉴리스트 띄우기
	public List<MenuEntity>findAllBymUsername(int sNum){
		return tpl.selectList("menuMapper.findAllById",sNum);
	}
	
	//메뉴읽기
	public MenuEntity findById(Integer menuno) {
		System.out.println("####");
		System.out.println(menuno);
		return tpl.selectOne("menuMapper.findById",menuno);
		
	}
	
	//메뉴추가
	public int insert(MenuEntity menu) {
		return tpl.insert("menuMapper.insert",menu);
	}
	
	//메뉴업뎃
	public int update(MenuEntity menu) {
		return tpl.update("menuMapper.update",menu);
	}
	
	//메뉴삭제
	public int delete(Integer menuno) {
		return tpl.delete("menuMapper.delete",menuno);
	}

	//메뉴전체삭제
	public int deleteBySnum(int sNum) {
		return tpl.delete("menuMapper.deleteBySnum",sNum);
	}
	
	public List<MenuEntity> findAllById(String mUsername) {
		return tpl.selectList("menuMapper.findAllById",mUsername);
	}
}
