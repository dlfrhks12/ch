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

	// 주문을 위한 메뉴읽기 (전체회원 보기가능)
	public List<MenuEntity> orderMenuRead(int sNum) {
		return tpl.selectList("menuMapper.findAllById", sNum);
	}

	// 카트에 메뉴 번호 넘겨주기 위함
	public MenuEntity findBymenuno(Integer menuno) {
		System.out.println("메뉴디에이오 시작 =================================");
		System.out.println("매뉴다애이오 매뉴번호 : " + menuno);
		System.out.println("메뉴디에이오 끝 =================================");
		return tpl.selectOne("menuMapper.findBymenuno", menuno);
	}

	//////////////////////////////////////////////////

	// 메뉴리스트
	public List<MenuEntity> findAll() {
		return tpl.selectList("menuMapper.findAll");
	}

	// 메뉴리스트 띄우기
	public List<MenuEntity> findAllBymUsername(int sNum) {
		return tpl.selectList("menuMapper.findAllById", sNum);
	}

	// 메뉴읽기
	public MenuEntity findById(Integer menuno) {
		return tpl.selectOne("menuMapper.findById", menuno);
	}

	// 메뉴추가
	public int insert(MenuEntity menu) {
		return tpl.insert("menuMapper.insert", menu);
	}

	// 메뉴수정
	public int update(MenuEntity menu) {
		return tpl.update("menuMapper.update", menu);
	}

	// 메뉴삭제
	public int delete(Integer menuno) {
		return tpl.delete("menuMapper.delete", menuno);
	}

	// 메뉴전체삭제
	public int deleteBySnum(int sNum) {
		return tpl.delete("menuMapper.deleteBySnum", sNum);
	}

	public List<MenuEntity> findAllById(String mUsername) {
		return tpl.selectList("menuMapper.findAllById", mUsername);
	}
}
