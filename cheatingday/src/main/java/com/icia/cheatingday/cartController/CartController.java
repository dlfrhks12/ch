package com.icia.cheatingday.cartController;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icia.cheatingday.cart.CartDto;
import com.icia.cheatingday.cart.CartEntity;
import com.icia.cheatingday.cart.CartService;
import com.icia.cheatingday.cart.ProductService;

@Controller
public class CartController {
	@Inject
	private CartService cartservice;

	@Inject
	private ProductService proservice;

	private ObjectMapper objectMapper = new ObjectMapper();

	// ********************************* 메뉴 리스트
	// ****************************************
	@GetMapping("/cart/list")
	public ModelAndView list() {
//		return new ModelAndView("cart/cartlist").addObject("list", proservice.list());
		return new ModelAndView("main").addObject("viewName", "cart/cartlist.jsp");
	}
// ***********************************************
	
	 // 장바구니  추가--------------------------
    @PostMapping("/cart/add")
    public ResponseEntity<?> insert(CartEntity cart, Principal principal) {
        System.out.println("컨트롤러+++++++++++++++++++++++++++++++++++++++++++++");
       	System.out.println("컨트롤러 카트 : " + cart);
       	System.out.println("컨트롤러 해당아이디 : " + principal);
       	System.out.println("컨트롤러 끝 ----------------------------------------------");
        return ResponseEntity.ok(cartservice.add(cart));
    }
    
    // 장바구니 리스트
    @GetMapping("/cart/cartview")
	public ModelAndView view(String uUsername) {
		return new ModelAndView("main").addObject("viewName", "cart/cartview").addObject("cartList", cartservice.findAllCartNo(uUsername));
	}
    
    @GetMapping("/cart/read")
	public ResponseEntity<?> read(Principal principal) throws JsonProcessingException {
		return ResponseEntity.ok(cartservice.findAllCartNo(principal.getName()));
	}
    
	
    // 수량
 	@PostMapping("/cart/change")
 	public ResponseEntity<?> change(int mNo, boolean isIncrese) {
 		CartEntity cart = cartservice.change(mNo, isIncrese);
 		return ResponseEntity.ok(cart);
 	}
 	
 	@DeleteMapping("/cart/cartDelete")
 	public ResponseEntity<?> delete(String mNo, Principal principal) throws JsonParseException, JsonMappingException, IOException {
 		System.out.println("deleteController artno======="+mNo);
        List<Integer> list = objectMapper.readValue(mNo, new TypeReference<List<Integer>>() {});
        List<CartDto.DtoForList> bagList = cartservice.multipleDelete(list, principal.getName());
        return ResponseEntity.ok(bagList);
 	}
		
		
		
		
/*
	// ***************************** 장바구니
	// ********************************************
	@GetMapping("/cart/view")
	public ModelAndView view(int cartNo) {
		return new ModelAndView("cart/view").addObject("cartview", cartservice.findAllCartNo(cartNo));
	}
	

	@GetMapping("/cart/read")
	public ResponseEntity<?> read() throws JsonProcessingException {
		return ResponseEntity.ok(cartservice.read());
	}

	// 수량
	@PostMapping("/cart/change")
	public ResponseEntity<?> change(int cartNo, boolean isIncrese) {
		CartEntity cart = cartservice.change(cartNo, isIncrese);
		return ResponseEntity.ok(cart);
	}
	
	*/
	
/*
	@RequestMapping(value="/cart/delete" , method={RequestMethod.POST})
	@DeleteMapping("/cart/delete")
	public ResponseEntity<?> delete(HttpSession session, int cartNo) {
		System.out.println("컨트롤러 시작 +++++++++++++++++++++++++++++++++++++");
		System.out.println("컨트롤러 세션 : " + session);
		System.out.println("컨트롤러 카트번호 : " + cartNo);
		
		List<CartEntity> cartList = cartservice.delete(session, cartNo);
		System.out.println("컨트롤러 카트리스트 : " + cartList);
		System.out.println("컨트롤러 끝 --------------------------------------");
		return ResponseEntity.ok(cartList);
	}
	*/
	/*
	@RequestMapping(value="/cart/delete_all" , method={RequestMethod.POST})
	@DeleteMapping("/cart/delete_all")
	public ResponseEntity<?> delete(String pnos, int cartNo)
			throws JsonParseException, JsonMappingException, IOException {
		List<Integer> list = objectMapper.readValue(pnos, new TypeReference<List<Integer>>() {});
		List<CartEntity> bagList = cartservice.multipleDelete(list,cartNo);
		return ResponseEntity.ok(bagList);
	}
*/
}
