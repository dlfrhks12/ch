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
	public List<MenuEntity> findAll(int menuno){
		return tpl.selectList("menuMapper.findAll",menuno);
	}
	
	//메뉴읽기
	public MenuEntity findById(int menuno) {
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
}
