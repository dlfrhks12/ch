package com.icia.cheatingday.cart;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.cheatingday.manager.entity.Store;

@Repository
public class OrdersDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public int insertOrderAll(List<Orders> orders) {
		return tpl.insert("orderMapper.insertOrderAll",orders);
	}
	
	// 주문상세 출력
	public List<Orders> findAlls() {
		return tpl.selectList("orderMapper.findAlls");
	}
	public List<Orders> findAllByusername(int startRowNum,int endRowNum,String uUsername) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRowNum",startRowNum);
		map.put("endRowNum", endRowNum);
		map.put("uUsername", uUsername);
		return tpl.selectList("orderMapper.findAllByusername", map);
	}

	public Orders findByOrderNo(int orderNo) {
		return tpl.selectOne("orderMapper.findByOrderNo",orderNo);
	}
	//사업자
	//주문 리스트 출력 페이징 - 검색용
	public List<Orders> orderListBySNumKeyword(int startRowNum,int endRowNum, int sNum, String keyword){ 
		Map<String, Object> map = new HashMap<>();
		map.put("startRowNum",startRowNum);
		map.put("endRowNum", endRowNum);
		map.put("sNum", sNum);
		map.put("keyword", keyword);
		return tpl.selectList("orderMapper.Search", map); 
	}
	
	
	//주문 리스트 출력 페이징 
	public List<Orders> orderListBySNum(int startRowNum, int endRowNum, int sNum) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRowNum",startRowNum);
		map.put("endRowNum", endRowNum);
		map.put("sNum", sNum);
		return tpl.selectList("orderMapper.orderListBySNum", map); 
	}


	
	
	//사업자
	//주문 리스트 sNum 조건설정 - 검색용
	public int countBysNumKeyword(int sNum, String keyword){
		Map<String,Object> map = new HashMap<>();
		map.put("sNum", sNum);
		map.put("keyword", keyword);
		return tpl.selectOne("orderMapper.countBysNum",map);
	}
	
	///////주문리스트 
	public int countBysNum(int sNum) {
		return tpl.selectOne("orderMapper.countBysNum",sNum);
	}
	
	
	//사업자> 주문리스트에 제목보이기 
	public int countByOno(int orderNo) {
		return tpl.selectOne("orderMapper.countByOno",orderNo);
	}
	

	//사업자> 주문 읽기
	public List<Orders> orderReadByONo(int orderNo) {
		return tpl.selectList("orderMapper.orderReadByONo",orderNo);
	}
	
	//리스트때 이름읽기
	public Orders menuName(int sNum) {
		return tpl.selectOne("orderMapper.menuName",sNum);
	}
	
	public int total() {
		return tpl.selectOne("orderMapper.total");
	}

	
	
	public int findByONo(int orderNo) {
		return tpl.selectOne("orderMapper.findByONo", orderNo);
	}
	
}
