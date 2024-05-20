package com.bookstore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.frontend.shopingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderServices {
	private OrderDAO orderDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.orderDAO = new OrderDAO();
	}
	
	public void listAllOrders() throws ServletException, IOException {
		 listAllOrders(null);
	}

	public void listAllOrders(String message) throws ServletException, IOException {
		List<BookOrder> listOrder = orderDAO.listAll();
		request.setAttribute("listOrder", listOrder);
		if(message != null) {
			request.setAttribute("message", message);
		}
		String orderListPage = "order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(orderListPage);
		dispatcher.forward(request, response);

	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		BookOrder order = orderDAO.get(orderId);
		request.setAttribute("order", order);
		String detailpage = "order_detail.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(detailpage);
		requestDispatcher.forward(request, response);
	}

	public void showChekcoutForm() throws ServletException, IOException {
		String checkoutPage = "frontend/checkout.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(checkoutPage);
		requestDispatcher.forward(request, response);

	}

	public void placeOrder() throws ServletException, IOException {
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String address = request.getParameter("adress");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		String paymentMethod = request.getParameter("paymentMethod");
		String shippingAddress = address + " , " + city + " , " + zipcode + " , " + country;

		BookOrder bookOrder = new BookOrder();
		bookOrder.setRecipientName(recipientName);
		bookOrder.setRecipientPhone(recipientPhone);
		bookOrder.setShippingAddress(shippingAddress);
		bookOrder.setPaymentMethod(paymentMethod);

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		bookOrder.setCustomer(customer);
		ShoppingCart shopingCart = (ShoppingCart) session.getAttribute("cart");
		Map<Book, Integer> items = shopingCart.getItems();

		Set<OrderDetail> orderDeatails = new HashSet<>();
		Iterator<Book> iterator = items.keySet().iterator();
		while (iterator.hasNext()) {
			Book book = iterator.next();
			Integer qyantity = items.get(book);
			float subToatal = qyantity * book.getPrice();
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(book);
			orderDetail.setBookOrder(bookOrder);
			orderDetail.setQuantity(qyantity);
			orderDetail.setSubtotal(subToatal);
			orderDeatails.add(orderDetail);
		}
		bookOrder.setOrderDetails(orderDeatails);
		bookOrder.setTotal(shopingCart.getTotalAmount());
		orderDAO.create(bookOrder);
		String message = "Thenk you, your order has been recievede" + "We will deliver your book in few days ";

		request.setAttribute("message", message);
		String messagePage = "frontend/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		requestDispatcher.forward(request, response);
	}

	public void listOrdersByCustomers() throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		Customer customer = (Customer) httpSession.getAttribute("loggedCustomer");
		List<BookOrder> listOrders = orderDAO.listByCustomer(customer.getCustomerId());
		request.setAttribute("listOrders", listOrders);
		String historyPage = "frontend/order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(historyPage);
		dispatcher.forward(request, response);

	}

	public void showOrderDetailForCustomer() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		
		BookOrder order = orderDAO.get(orderId, customer.getCustomerId());
		
		request.setAttribute("order", order);
		String detailpage = "frontend/order_detail.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(detailpage);
		requestDispatcher.forward(request, response);
	}

	public void showEditOrderForm() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		Object isPendingBook = session.getAttribute("NewBookPendingToAddToOrder");

		if (isPendingBook == null) {
			BookOrder order = orderDAO.get(orderId);
			session.setAttribute("order", order);
		} else {
			session.removeAttribute("NewBookPendingToAddToOrder");
		}
		String editPage = "order_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);

	}

	public void updateOrder() throws ServletException, IOException {
		HttpSession session = request.getSession();
		BookOrder order = (BookOrder) session.getAttribute("order");
		
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String shippingAddress = request.getParameter("shippingAddress");
		String paymentMethod = request.getParameter("paymentMethod");
		String orderStatus = request.getParameter("orderStatus");
		
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);
		order.setStatus(orderStatus);
		
		String[] arrayBookId = request.getParameterValues("bookId");
		String[] arrayPrice = request.getParameterValues("price");
		String[] arrayQuantity = new String[arrayBookId.length];
		
		for (int i = 1; i <= arrayQuantity.length; i++) {
			arrayQuantity[i - 1] = request.getParameter("quantity" + i);
		}
		
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		orderDetails.clear();
		
		float totalAmount = 0.0f;
		
		for (int i = 0; i < arrayBookId.length; i++) {
			int bookId = Integer.parseInt(arrayBookId[i]);
			int quantity = Integer.parseInt(arrayQuantity[i]);
			float price = Float.parseFloat(arrayPrice[i]);
			
			float subtotal = price * quantity;
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(new Book(bookId));
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			orderDetail.setBookOrder(order);
			
			orderDetails.add(orderDetail);
			
			totalAmount += subtotal;
		}
		
		order.setTotal(totalAmount);
		
		orderDAO.update(order);
		
		String message = "The order " + order.getOrderId() + " has been updated successfully";
		
		listAllOrders(message);
	}
	
	public void deleteOrder() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		
		BookOrder order = orderDAO.get(orderId);
		
		if (order != null) {		
			orderDAO.delete(orderId);
		
			String message = "The order ID " + orderId + " has been deleted.";
			listAllOrders(message);
		} else {
			String message = "Could not find order with ID " + orderId +
					", or it might have been deleted by another admin.";
			
		}
	}

}
