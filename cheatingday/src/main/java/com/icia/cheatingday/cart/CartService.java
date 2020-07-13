package com.icia.cheatingday.cart;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.icia.cheatingday.cart.CartDto.DtoForList;

@Service
public class CartService {
	@Inject
	private CartDao cartdao;
	@Inject
	private ProductDao prodao;
	@Inject
	private ModelMapper modelMapper;
	
	// 1. 장바구니에 추가
	public int add(CartEntity cart) {
		System.out.println("서비스 +++++++++++++++++++++++++++++++++++++");
		System.out.println("서비스 카트 : " + cart);
		
		int mNo = cart.getMNo();
		System.out.println("서비스 메뉴번호 : " + mNo);
		
		ProductEntity pro = prodao.findByMNo(mNo);
		System.out.println("서비스 메뉴엔티티 : " + pro);
		
		cart.setCartJumunMoney(cart.getCartCount()*pro.getMPrice());
		System.out.println("서비스 마지막 카트 : " + cart );
		System.out.println("서비스 끝 -------------------------------------");
		
		return cartdao.insert(cart);
	}
	
	
	
	
	public int insertByBag(String uUsername, CartDto.DtoForList dto) {
		CartEntity cart = modelMapper.map(dto, CartEntity.class);
		ProductEntity pro = modelMapper.map(dto, ProductEntity.class);
		return cartdao.insert(cart);
	}
	
	public List<CartDto.DtoForList> findAllCartNo(String uUsername) {
		CartDto.DtoForList dto1 = new CartDto.DtoForList();
		List<CartEntity> cartList = cartdao.findCartList(uUsername);
		List<CartDto.DtoForList> dtoList = new ArrayList<>();
		for(CartEntity cart1:cartList) {
			
			int mNo = cart1.getMNo();
			
			ProductEntity pro = prodao.findByMNo(mNo);
			
			CartDto.DtoForList dtocart = modelMapper.map(cart1, CartDto.DtoForList.class);
			dtocart.setPro(pro);
			dtoList.add(dtocart);
		}
		return dtoList;
	}
	

	// 수량
	public CartEntity change(Integer mNo, boolean isIncrese) {
		CartEntity cart = cartdao.findByCartNo(mNo);
		ProductEntity pro = prodao.findByMNo(mNo);
		
		if (isIncrese == true) {
			cart.setCartCount(cart.getCartCount() + 1);
			cart.setCartJumunMoney(cart.getCartCount() * pro.getMPrice());
			cartdao.increaseByAmount(mNo);
			cartdao.cartUpdate(CartEntity.builder()
					.mNo(mNo)
					.cartCount(cart.getCartCount())
					.cartJumunMoney(cart.getCartCount() * pro.getMPrice())
					.build());
		} else {
			if (cart.getCartCount() > 1) {
				cart.setCartCount(cart.getCartCount() - 1);
				cart.setCartJumunMoney(cart.getCartCount() * pro.getMPrice());
				cartdao.decreaseByAmount(mNo);
				cartdao.cartUpdate(CartEntity.builder()
						.mNo(mNo)
						.cartCount(cart.getCartCount())
						.cartJumunMoney(cart.getCartCount()* pro.getMPrice())
						.build());
			}
		}
		return cart;
	}

	// 상품이 장바구니 몇번째에 위치하는 지 검색하는 함수
	// 상품이 없을 경우 -1을 리턴
	private int findCart(List<CartDto.DtoForList> cartList, int mNo) {
		System.out.println("서비스 findCart 삭제전 리스트 +++++++++++++++++++++++++++++++++++++++");
		System.out.println("서비스 findCart 카트리스트 : " + cartList);
		System.out.println("서비스 findCart 카트번호 : " + mNo);
		System.out.println("서비스 findCart 삭제전 리스트 끝 -------------------------------------");
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getMNo() == mNo)
				return i;
		}
		return -1;
	}
	
	// 8. 선택한 상품 삭제
	public List<DtoForList> multipleDelete(List<Integer> list, String uUsername) {
		System.out.println("서비스2 시작 2 +++++++++++++++++++++++++++++++++++++++");
		System.out.println("서비스2 리스트 : " + list);
		
		List<DtoForList> cartList = findAllCartNo(uUsername);
		System.out.println("서비스2 카트리스트 : " + cartList);
		
		List<Integer> deleteIndexList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			int idx = findCart(cartList, list.get(i));
			deleteIndexList.add(idx);
		}
		for (int i = deleteIndexList.size() - 1; i >= 0; i--) {
			int idx = deleteIndexList.get(i);
			cartList.remove(idx);
			cartdao.cartDelete(list.get(i));
		}
		System.out.println("서비스2 카트리스트 : " + cartList);
		System.out.println("서비스2 끝 ---------------------------------------");
		return cartList;
	}
	

	/*
	public List<CartEntity> deleteByCart(List<Integer> list, int cartNo) {
		System.out.println("서비스 삭제 부분+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("서비스 리스트  : " + list);
		System.out.println("서비스 카트번호  : " + cartNo);
		
		List<CartEntity> cartList = cartList();
		System.out.println("서비스 카트리스트 : " + cartList);
		
		List<Integer> deleteList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			int idx = findCart(cartList, list.get(i));
			System.out.println("서비스 인덱스 : " + idx);
			deleteList.add(idx);
		}
		for (int i = deleteList.size() - 1; i >= 0; i--) {
			int idx = deleteList.get(i);
			cartList.remove(idx);
			cartdao.cartDelete(list.get(i));
		}
		System.out.println("서비스 끝 리스트 : " + cartList);
		System.out.println("서비스 끝------------------------------------------------");
		return cartList;
	}
	
	*/
}
