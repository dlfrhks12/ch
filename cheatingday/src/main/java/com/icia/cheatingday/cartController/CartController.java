package com.icia.cheatingday.cartController;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icia.cheatingday.cart.CartEntity;
import com.icia.cheatingday.cart.CartService;
import com.icia.cheatingday.manager.service.StoreService;

@Controller
public class CartController {
   @Autowired
   private CartService service;
   @Autowired
   private ObjectMapper objectMapper = new ObjectMapper();
   @Autowired
   private StoreService storeService;
   
   //예
   //주문하기위해 가게읽기 페이지로 이동
   ////주문을 위한 메뉴읽기 (전체회원 보기가능)
   @GetMapping("/order/orderPage")
   public ModelAndView orderStoreRead(int sNum) {
      return new ModelAndView("main").addObject("viewName", "order/orderPage.jsp")
            .addObject("viewHeader", "include/menuheader.jsp")
            .addObject("storeRead", storeService.orderStoreRead(sNum))
            .addObject("cartlist", service.orderMenuRead(sNum));
   }
   
   
   // 장바구니 리스트출력
   @GetMapping("/cart/cartview2")
   public ModelAndView view()throws JsonProcessingException{
      return new ModelAndView("main")
            .addObject("viewHeader", "include/noheader.jsp")
            .addObject("viewName", "cart/cartview2.jsp");
         
   }
   
   
   // 장바구니 담기 했을 때 담기는거 출력
   @GetMapping("/cart/read")
   public ResponseEntity<?> read(HttpSession session) {
      List<CartEntity> cartList = service.read(session);
      return ResponseEntity.ok(cartList);
   }
   
   // 장바구니 담기 insert
   @PostMapping("/cart/add")
   public ResponseEntity<?> insert(Integer menuno, HttpSession session, Principal prin) {
      List<CartEntity> cartList = service.add(session, menuno, prin.getName());
      session.setAttribute("cartList", cartList);
      return ResponseEntity.ok(cartList);
   }

   // 개수 증가 감소
   @PatchMapping("/cart/change")
   public ResponseEntity<?> change(HttpSession session, boolean isIncrease, Integer menuno) {
      CartEntity cart = service.change(session, isIncrease, menuno);
      return ResponseEntity.ok(cart);
   }

   @DeleteMapping("/cart/delete")
   public ResponseEntity<?> delete(HttpSession session, Integer menuno) {
      List<CartEntity> cartList = service.delete(session, menuno);
      return ResponseEntity.ok(cartList);
   }

   // JSON으로 넘겨주기위해 String pnos(menuno)를 사용
   @DeleteMapping("/cart/delete_all")
   public ResponseEntity<?> multipleDelete(HttpSession session, String pnos)
         throws JsonParseException, JsonMappingException, IOException {
      List<Integer> list = objectMapper.readValue(pnos, new TypeReference<List<Integer>>() {
      });
      List<CartEntity> cartList = service.multipleDelete(session, list);
      return ResponseEntity.ok(cartList);
   }
   
   // 임시 장바구니 주문 상세
   @GetMapping("/cart/orders")
   public ModelAndView oDetails()throws JsonProcessingException{
      return new ModelAndView("main")
            .addObject("viewHeader", "include/noheader.jsp")
            .addObject("viewName", "cart/orders.jsp")
            .addObject("orders", service.findAll());
         
   }
   
   @PostMapping("/cart/order")
   public ResponseEntity<?> insert(HttpSession session) {
      return ResponseEntity.ok(service.insertAll(session));
   }
   
   // 최종 주문 내용
   @PostMapping("/cart/finalorder")
   public ResponseEntity<?> inserts(HttpServletRequest req, int orderNo, Principal principal) {
	   return ResponseEntity.ok(service.insertOrderAll(req, orderNo,principal.getName()));
   }

}