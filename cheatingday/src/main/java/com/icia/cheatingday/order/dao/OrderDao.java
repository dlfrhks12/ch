package com.icia.cheatingday.order.dao;

import java.util.*;

import javax.inject.*;

import org.mybatis.spring.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.order.entity.*;
import com.icia.cheatingday.review.entity.Review;

@Repository
public class OrderDao {
	@Inject
	private SqlSessionTemplate tpl;
	
	
	//사업자> 주문 리스트 
		public List<OrderEntity> orderListBySNum(int startRowNum, int endRowNum, int sNum){ 
			Map<String, Integer> map = new HashMap<>();
			map.put("startRowNum",startRowNum);
			map.put("endRowNum", endRowNum);
			map.put("sNum", sNum);
			return tpl.selectList("orderMapper.orderListBySNum", map); 
		}
		
	//사업자> 주문리스트 sNum
		public int countBysNum(int sNum){
			return tpl.selectOne("orderMapper.countBysNum",sNum);
		}
	 
	//사업자> 주문리스트에 제목보이기 
		public DetailorderEntity ordercheck(int oNo) {
			return tpl.selectOne("orderMapper.ordercheck",oNo);
		}

	//사업자> 주문리스트에 제목보이기 
		public int countByOno(int oNo) {
			return tpl.selectOne("orderMapper.countByOno",oNo);
		}
		
////////////////////////////////////////////////////////////////////////////////	
		
	//사업자> 주문 읽기
	public List<DetailorderEntity> orderReadByONo(int oNo) {
		return tpl.selectList("orderMapper.orderReadByONo",oNo);
	}
	
	
	
	// 1. 장바구니 추가
	public int orderInsert(DetailorderEntity order) {
		return tpl.insert("orderMapper.orderInsert", order);
	}
	
	// 2. 장바구니 목록
	public List<DetailorderEntity> order(String uUsername) {
		return tpl.selectList("orderMapper.order", uUsername);
	}
	
	// 3. 장바구니 삭제
	public int orderDelete(int dNo) {
		return tpl.delete("orderMapper.orderDelete", dNo); 
	}
	
	// 4. 장바구니 수정?
	public int orderUpdate(DetailorderEntity order) {
		return tpl.update("orderMapper.orderUpdate", order);
	}
	
	// 5. 장바구니 금액 합계
	public int orderAllSum(String uUsername) {
		return tpl.selectOne("orderMapper.orderAllSum", uUsername);
	}
	
	// 6. 장바구니 상품수량변경
	public int orderUpdatedCnt(DetailorderEntity order) {
		return tpl.update("orderMapper.orderUpdatedCnt", order);
	}
	
	public List<DetailorderEntity> myBas(String uUsername) {
		return tpl.selectList("orderMapper.basList", uUsername);
	}
	
	
	public List<OrderEntity> resList() {
		return tpl.selectList("orderMapper.resList");
	}
	
	
}
