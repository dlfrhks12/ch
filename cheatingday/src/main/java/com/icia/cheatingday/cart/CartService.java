package com.icia.cheatingday.cart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.cheatingday.exception.CartFailException;
import com.icia.cheatingday.manager.dao.MenuDao;
import com.icia.cheatingday.manager.entity.MenuEntity;

@Service
public class CartService {
   @Autowired
   private MenuDao menuDao;
   @Inject
   private OrderDetailsDao cartDao;
   @Inject
   private ProductDao proDao;
   @Inject
   private OrdersDao orDao;
   
   // 장바구니가 없으면 새로 만들고, 있으면 꺼내는 메소드
   private List<CartEntity> findList(HttpSession session) {
      if (session.getAttribute("cartview") == null) {
         List<CartEntity> list = new ArrayList<CartEntity>();
         session.setAttribute("cartview", list);
         return list;
      } else 
         return (List<CartEntity>) session.getAttribute("cartview");
   }

   // 상품이 장바구니 몇번째에 위치하는 지 검색하는 함수
   // 상품이 없을 경우 -1을 리턴
   private int findCart(List<CartEntity> cartList, Integer menuno) {
      for (int i = 0; i < cartList.size(); i++) {
         if (cartList.get(i).getMenuno() == menuno)
            return i;
      }
      return -1;
   }

   //주문을 위한 메뉴읽기 (전체회원 보기가능)
   public List<MenuEntity> orderMenuRead(int sNum){
      List<MenuEntity> list = menuDao.orderMenuRead(sNum);
      return list;
   }
   
   // 1. 장바구니 출력
   public List<CartEntity> read(HttpSession session) {
      return findList(session);
   }
   
   // 2. 재고 확인
   public boolean checkStock(Integer menuno, HttpSession session) {
      List<CartEntity> cartList = findList(session);
      int idx = findCart(cartList, menuno);
      // 장바구니에서 상품을 찾을 수 없는 경우
      if (idx == -1)
         throw new CartFailException("장바구니에서 상품을 찾을 수 없습니다");
      return true;
   }

   // 3. 장바구니에 추가
   public List<CartEntity> add(HttpSession session, Integer menuno, String uUsername) {
      List<CartEntity> cartList = findList(session);
      int idx = findCart(cartList, menuno);
      CartEntity cart = new CartEntity();

      // 장바구니에 이미 존재하고 재고가 모자라지 않은 경우 개수 증가
      if (idx >= 0) {
         cartList.get(idx).increase();
      } else {
    	  MenuEntity product = menuDao.findBymenuno(menuno);
         cart = new CartEntity(
        	   product.getMenuno(), 
               uUsername,
               product.getMenuname(),
               product.getMenusal(),
               LocalDateTime.now(), 
               1, 
               product.getMenusajin(),
               product.getMenusal(),
               product.getSNum()
               );
	   
         cartList.add(cart);
      }
      session.setAttribute("cartList", cartList);
      return cartList;
   }

   // 4, 5. 개수 변경
   public CartEntity change(HttpSession session, boolean isIncrease, Integer menuno) {
      List<CartEntity> cartList = findList(session);
      int idx = findCart(cartList, menuno);
      if (idx == -1)
         throw new CartFailException("장바구니에서 상품을 찾을 수 없습니다");
      if (isIncrease == true) {
         cartList.get(idx).increase();
      } else {
         int countOfProduct = cartList.get(idx).getCartCount();
         if (countOfProduct < 1)
            throw new CartFailException("최소 구매개수는 1개입니다");
         cartList.get(idx).decrease();
      }
      session.setAttribute("cartList", cartList);
      return cartList.get(idx);
   }

   // 6. 상품 삭제
   public List<CartEntity> delete(HttpSession session, int menuno) {
      List<CartEntity> cartList = findList(session);
      int idx = findCart(cartList, menuno);
      if (idx == -1)
         throw new CartFailException("장바구니에서 상품을 찾을 수 없습니다");
      cartList.remove(idx);
      session.setAttribute("cartList", cartList);
      return cartList;
   }

   // 8. 선택한 상품 삭제
   public List<CartEntity> multipleDelete(HttpSession session, List<Integer> list) {
      List<CartEntity> cartList = findList(session);
      List<Integer> deleteIndexList = new ArrayList<>();
      for (int i = 0; i < list.size(); i++) {
         int idx = findCart(cartList, list.get(i));
         deleteIndexList.add(idx);
      }
      for (int i = deleteIndexList.size() - 1; i >= 0; i--) {
         int idx = deleteIndexList.get(i);
         cartList.remove(idx);
      }
      session.setAttribute("cartList", cartList);
      return cartList;
   }

	// 9. 상품 주문
	public int insertAll(HttpSession session) {
		List<CartEntity> cartList = findList(session);
		session.setAttribute("cartList", cartList);
		List<OrderDetails> list = (List<OrderDetails>) session.getAttribute("cartList");
		cartDao.deletes();
		return cartDao.insertAll(list);
	}
	
	// 주문상세 내역 리스트
	public List<OrderDetails> findAll() {
		return cartDao.findAll();
	}
	
	// 결제 눌렀을때
	public int insertOrderAll(HttpServletRequest req) {
		HttpSession session = req.getSession();
		List<OrderDetails> order = findAll();
		session.setAttribute("order", order);
		
		List<Orders> orders = (List<Orders>) session.getAttribute("order");
		return orDao.insertOrderAll(orders);
	}
	
	public List<Orders> findAlls() {
		return orDao.findAlls();
	}
}
/*
 * for(int i = 0; i<order.size(); i++) { orders = (List<Orders>) order.get(i);
 * orDao.insertOrderAll(orders); }
 */