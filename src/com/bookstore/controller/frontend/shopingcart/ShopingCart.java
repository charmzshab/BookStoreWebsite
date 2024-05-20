package com.bookstore.controller.frontend.shopingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bookstore.entity.Book;

public class ShopingCart {
	private Map<Book, Integer> cartColl = new HashMap<>();

	public void addItem(Book book) {
		if (cartColl.containsKey(book)) {
			Integer quantity = cartColl.get(book) + 1;
			cartColl.put(book, quantity);
		} else {
			cartColl.put(book, 1);
		}
	}

	public void updateCart(int[] bookIds, int[] quantities) {
		for (int i = 0; i < bookIds.length; i++) {
			Book book = new Book(bookIds[i]);
			Integer value = quantities[i];
			cartColl.put(book, value);

		}
	}

	public int getTotalQyantity() {
		int total = 0;
		Iterator<Book> iterator = cartColl.keySet().iterator();
		while (iterator.hasNext()) {
			Book book = iterator.next();
			Integer quant = cartColl.get(book);
			total += quant;
		}
		return total;
	}

	public double getTotalAmount() {
		double total = 0.0f;

		Iterator<Book> iterator = cartColl.keySet().iterator();

		while (iterator.hasNext()) {
			Book book = iterator.next();
			Integer quant = cartColl.get(book);
			double subTotal = quant * book.getPrice();
			total += subTotal;
		}
		return total;
	}

	public void clear() {
		cartColl.clear();
	}

	public int getTotalItems() {
		return cartColl.size();
	}

	public void removeItem(Book book) {
		cartColl.remove(book);
	}

	public Map<Book, Integer> getItems() {
		return this.cartColl;
	}

}
