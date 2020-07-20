package com.icia.cheatingday.cartController;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

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
import com.icia.cheatingday.manager.entity.MenuEntity;
import com.icia.cheatingday.manager.service.ManagerService;
import com.icia.cheatingday.manager.service.StoreService;

@Controller
public class CartController {
	@Autowired
	private CartService service;
	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private StoreService storeService;
	@Autowired
	private ManagerService managerService;
	

	
	
	
	//예
	//주문하기위해 가게읽기 페이지로 이동
	////주문을 위한 메뉴읽기 (전체회원 보기가능)
	@GetMapping("/order/orderPage")
	public ModelAndView orderStoreRead(int sNum) {
		return new ModelAndView("main").addObject("viewName", "order/orderPage.jsp")
				.addObject("viewHeader", "include/noheader.jsp")
				.addObject("storeRead", storeService.orderStoreRead(sNum))
				.addObject("cartlist", service.orderMenuRead(sNum));
	}
	
	
	/*// 메뉴 리스트 출력
	@GetMapping("/cart/cartlist")
	public ModelAndView list() throws JsonProcessingException {
		return new ModelAndView("main")
				.addObject("viewName", "order/orderPage.jsp")
				.addObject("viewHeader", "include/noheader.jsp")
				.addObject("cartlist", service.list());
	}*/
	
	
	// 장바구니 리스트출력
	@GetMapping("/cart/cartview")
	public ModelAndView view()throws JsonProcessingException{
		return new ModelAndView("main")
				.addObject("viewHeader", "include/noheader.jsp")
				.addObject("viewName", "cart/cartview.jsp");
			
	}
	
	
	// 장바구니 담기 했을 때 담기는거 출력
	@GetMapping("/cart/read")
	public ResponseEntity<?> read(HttpSession session) {
		//System.out.println("컨트롤러 read 시작 +++++++++++++++++++++++++++++");
		//System.out.println("컨트롤러 read 세션 : " + session);
		List<CartEntity> cartList = service.read(session);
		//System.out.println("컨트롤러 read 카트리스트 : " + cartList);
		//System.out.println("컨트롤러 read 끝 ---------------------------------");
		return ResponseEntity.ok(cartList);
	}
	
	// 장바구니 담기 insert
	@PostMapping("/cart/add")
	public ResponseEntity<?> insert(Integer menuno, HttpSession session, Principal prin) {
		System.out.println("컨트롤러 insert 시작 +++++++++++++++++++++++++");
		System.out.println("컨트롤러 insert 메뉴 번호 : " + menuno);
		System.out.println("컨트롤러 insert 세션 : " + session);
		
		List<CartEntity> cartList = service.add(session, menuno, prin.getName());
		System.out.println("컨트롤러 insert 카트리스트 : " + cartList);
		
		session.setAttribute("cartList", cartList);
		System.out.println("컨트롤러 insert 마지막 세션 : " + session);
		System.out.println("컨트롤러 insert 마지막 카트리스트 : " + cartList);
		System.out.println("컨트롤러 insert 끝 -----------------------------");
		
		return ResponseEntity.ok(cartList);
	}
	
	// 개수 증가 감소
	@PatchMapping("/cart/change")
	public ResponseEntity<?> change(HttpSession session, boolean isIncrease, Integer menuno) {
		//System.out.println("컨트롤러 개수 시작 ++++++++++++++++++++++++++++");
		//System.out.println("컨트롤러 개수 세션 : " + session);
		//System.out.println("컨트롤러 개수 개수 : " + isIncrease);
		//System.out.println("컨트롤러 개수 메뉴번호 : " + menuno);
		
		MenuEntity pro;
		CartEntity cart = service.change(session, isIncrease, menuno);
		//System.out.println("컨트롤러 개수 카트 : " + cart);
		//System.out.println("컨트롤러 개수 끝 ------------------------------");
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
	
	// 장바구니 담기 insert
	@PostMapping("/cart/orders")
	public ResponseEntity<?> orderinsert(HttpSession session, Principal prin) {

		int cartList = service.insert(session, prin.getName());

		session.setAttribute("cartList", cartList);


		return ResponseEntity.ok(cartList);
	}
}
