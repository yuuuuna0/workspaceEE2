package com.itwill.shop.cart.test;
import com.itwill.shop.cart.CartDao;
public class CartDaoTestMain {
	public static void main(String[] args) throws Exception{
		CartDao cartDao=new CartDao();
		System.out.println("--------cart list------");
		System.out.println(cartDao.getCartList("guard1"));
		System.out.println("--------cart add------");
		System.out.println(cartDao.add("guard3", 4, 3));
	}
}
