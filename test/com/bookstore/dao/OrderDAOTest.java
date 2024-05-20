package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

public class OrderDAOTest {

	private static OrderDAO orderDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDao = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDao.close();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order = new BookOrder();
		
		Customer customer = new Customer();
		customer.setCustomerId(3);
		
		order.setCustomer(customer);
		order.setRecipientName("Vuitalie Andrusca");
		order.setRecipientPhone("0777777777");
		order.setShippingAddress("Trandafirilor, 6/3,37 Rezina, Moldova");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();
		
		Book book = new Book(2);
		orderDetail.setBook(book);
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(1576.00f);
		orderDetail.setBookOrder(order);
		
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		BookOrder savedOrder = orderDao.create(order);
		assertTrue(savedOrder != null && savedOrder.getOrderDetails().size() > 0);

	}

	
	@Test
	public void testCreateBookOrder2() {
		BookOrder order = new BookOrder();
		
		Customer customer = new Customer();
		customer.setCustomerId(7);
		
		order.setCustomer(customer);
		order.setRecipientName("Zunea Zunea");
		order.setRecipientPhone("999999999");
		order.setShippingAddress("Arborilor, 45, Chisinau, Moldova");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail1 = new OrderDetail();
		
		
		Book book1 = new Book(7);
		orderDetail1.setBook(book1);
		orderDetail1.setQuantity(3);
		orderDetail1.setSubtotal(40.00f);
		orderDetail1.setBookOrder(order);
		orderDetails.add(orderDetail1);
		
		Book book2 = new Book(10);
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setBook(book2);
		orderDetail2.setQuantity(4);
		orderDetail2.setSubtotal(23.00f);
		orderDetail2.setBookOrder(order);
		orderDetails.add(orderDetail2);
		
		
		
		order.setOrderDetails(orderDetails);
		
		
         orderDao.create(order);
		
		assertTrue(order.getOrderId() > 0 && order.getOrderDetails().size() == 2);

	}
	
	
	@Test
	public void testCreateBookOrder3() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(8);
		
		order.setCustomer(customer);
		order.setRecipientName("Nam Ha Minh");
		order.setRecipientPhone("123456789");
		order.setShippingAddress("123 South Street, New York, USA");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail1 = new OrderDetail();
		
		Book book1 = new Book(10);
		orderDetail1.setBook(book1);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubtotal(50.5f);
		orderDetail1.setBookOrder(order);
		
		orderDetails.add(orderDetail1);

		Book book2 = new Book(5);
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setBook(book2);
		orderDetail2.setQuantity(1);
		orderDetail2.setSubtotal(40f);
		orderDetail2.setBookOrder(order);
		
		orderDetails.add(orderDetail2);
		
		order.setOrderDetails(orderDetails);
		
		orderDao.create(order);
		
		assertTrue(order.getOrderId() > 0 && order.getOrderDetails().size() == 2);
		
	}	

	@Test
	public void testUpdateBookOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAll() {
		fail("Not yet implemented");
	}

}
