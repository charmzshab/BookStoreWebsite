package com.bookstore.controller.frontend.shopingcart;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;

public class ShopingCartTest {
	private static ShoppingCart cart;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cart = new ShoppingCart();
		Book book = new Book(1);
		cart.addItem(book);
		cart.addItem(book);
	}

	@Test
	public void testAddItem() {
		Map<Book, Integer> returnedItems = cart.getItems();
		int quantity = returnedItems.get(new Book(1));
		assertEquals(2, quantity);
	}
	@Test
	public void testRemoveItem() {
		cart.removeItem(new Book(1));
		assertTrue(cart.getItems().isEmpty());
	}
	
	@Test
	public void testRemoveItem2() {
		Book book2 = new Book(2);
		cart.addItem(book2);
		cart.removeItem(book2);
		assertNull(cart.getItems().get(book2));
	}
	
	@Test
	public void testGetTotalQyantity() {
		Book book2 = new Book(3);
		cart.addItem(book2);
		cart.addItem(book2);
		cart.addItem(book2);
		assertEquals(5, cart.getTotalQuantity());
		
		
	}
	
	

	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
