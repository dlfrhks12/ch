package com.icia.cheatingday.cart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
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
   private OrdersDao ordersDao;
   @Autowired
   private ModelMapper model;
   // 장바구니가 없으면 새로 만들고, 있으면 꺼내는 메소드
   private List<CartEntity> findList(HttpSession session) {
      System.out.println("서비스 findList 시작 ++++++++++++++++++++++++++");
      System.out.println("서비스 findList 세션 : " + session);
      
      if (session.getAttribute("cartview") == null) {
         List<CartEntity> list = new ArrayList<CartEntity>();
         
         System.out.println("서비스 findList 리스트 : " + list);
         System.out.println("서비스 findList 끝 --------------------------");
         
         session.setAttribute("cartview", list);
         return list;
      } else 
         return (List<CartEntity>) session.getAttribute("cartview");
   }

   // 상품이 장바구니 몇번째에 위치하는 지 검색하는 함수
   // 상품이 없을 경우 -1을 리턴
   private int findCart(List<CartEntity> cartList, Integer menuno) {
      System.out.println("서비스 findCart 시작 ++++++++++++++++++++++++");
      System.out.println("서비스  findCart  카트리스트 : " + cartList);
      System.out.println("서비스  findCart  메뉴 번호 : " + menuno);
      
      for (int i = 0; i < cartList.size(); i++) {
         if (cartList.get(i).getMenuno() == menuno)
            return i;
      }
      System.out.println("서비스 findcart 카트리스트 아이 : " + cartList);
      System.out.println("서비스 findCart 끝 --------------------------");
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
      System.out.println("서비스 add 시작 ++++++++++++++++++++++++++++++++++");
      System.out.println("서비스 add 세션 : " + session);
      System.out.println("서비스 add 메뉴 번호  : " + menuno);
      
      List<CartEntity> cartList = findList(session);
      
      
      System.out.println("서비스 add 카트리스트 : " + cartList);
      
      int idx = findCart(cartList, menuno);
      System.out.println("서비스 add idx : " + idx);
      
      // 장바구니에 이미 존재하고 재고가 모자라지 않은 경우 개수 증가
      if (idx >= 0) {
         cartList.get(idx).increase();
      } else {
         System.out.println("카트서비스 메뉴 번호 : " + menuno);
         MenuEntity product = menuDao.findBymenuno(menuno);
         CartEntity cart = new CartEntity(product.getMenuno(), 
               uUsername,
               product.getMenuname(), 
               product.getMenusal(), 
               LocalDateTime.now(), 
               1, 
               product.getMenusajin(), 
               product.getSNum(),
               product.getMenusal());
         
         System.out.println("서비스  add 마지막 카트 : " + cart);
         cartList.add(cart);
      }

      session.setAttribute("cartList", cartList);
      
      System.out.println("서비스 카트리스트 : " + cartList);
      System.out.println("서비스 끝 ------------------------------------");
      
      return cartList;
   }

   // 4, 5. 개수 변경
   public CartEntity change(HttpSession session, boolean isIncrease, Integer menuno) {
      //System.out.println("서비스 개수 변경 시작 +++++++++++++++++++++++++++++++++++++");
      //System.out.println("서비스 개수 변경 세션 : " + session);
      //System.out.println("서비스 개수 변경 개수 증가 : " + isIncrease);
      //System.out.println("서비스 개수 변경 메뉴 번호 : " + menuno);
      
      List<CartEntity> cartList = findList(session);
      //System.out.println("서비스 개수 변경 카트리스트 : " + cartList);

      int idx = findCart(cartList, menuno);
      //System.out.println("서비스 개수 변경 idx : " + idx);
      
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
      //System.out.println("서비스 개수 변경 카트리스트 : " + cartList);
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
   public int insert (HttpSession session, String uUsername) {
	   List<CartEntity> cartList = findList(session);
	  /* for(CartEntity cartentity:cartList) {
		   Orders orders = model.map(cartentity, Orders.class);
		   orders.setOTotal(oTotal);
		   orders.set
	   }
	   for(CartEntity to : cartList) {
		   int oTotal = to.getCartJumunMoney();
		   oTotal+=oTotal;
		   cartList.get(oTotal);
	   }*/
	   session.setAttribute("cartList", cartList);
	   return ordersDao.insert(cartList);
	   }
}