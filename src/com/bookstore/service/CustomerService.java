package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerService {
	private CustomerDAO customerDAO;
	private HttpServletResponse response;
	private HttpServletRequest request;

	public CustomerService(HttpServletResponse response, HttpServletRequest request) {
		super();
		this.response = response;
		this.request = request;
		this.customerDAO = new CustomerDAO();
	}

	public void listCustomers(String message) throws ServletException, IOException {
		List<Customer> listCustomer = customerDAO.listAll();
		if (message != null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listCustomer", listCustomer);
		String path = "customer_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	public void listCustomers() throws ServletException, IOException {
		listCustomers(null);
	}

	private void updateCustomerFieldsFromForm(Customer customer) {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String country = request.getParameter("country");

		customer.setFullname(fullName);
		if (email != null && !"".equals(email)) {
			customer.setEmail(email);
		}
		if (password == null && "".equals(password)) {
			customer.setPassword(password);
		}
		customer.setFullname(fullName);
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setZipcode(zipCode);
		customer.setCity(city);
		customer.setCountry(country);
	}

	public void createCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		if (existCustomer != null) {
			String message = "Couk not create customer. Email " + email + " is already registred by another customer";
			listCustomers(message);
		} else {
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			customerDAO.create(newCustomer);
			String message = "New Customer has been created succesefully";
			listCustomers(message);
		}

	}

	public void editCustomer() throws ServletException, IOException {
		Integer customerID = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerID);
		request.setAttribute("customer", customer);
		String editPage = "customer_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);

	}

	public void updateCustomer() throws ServletException, IOException {
		int customerID = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");
		Customer customerByEmail = customerDAO.findByEmail(email);
		String message = null;
		if (customerByEmail != null && customerByEmail.getCustomerId() != customerID) {
			message = "Could not update the customer id " + customerID + "because  customer :";
		} else {
			Customer customerById = customerDAO.get(customerID);
			updateCustomerFieldsFromForm(customerById);
			customerDAO.update(customerById);
			message = "The customer has been update successefully";
		}
		listCustomers(message);
	}

	public void deleteCustomer() throws ServletException, IOException {
		Integer customerID = Integer.parseInt(request.getParameter("id"));
		customerDAO.delete(customerID);
		String message = "The customer has been deteleted successefuly";
		listCustomers(message);

	}

	public void registerCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		String message = "";
		if (existCustomer != null) {
			message = "Could not register you. Email " + email + " is already registred by another customer";
		} else {
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			customerDAO.create(newCustomer);
			message = "You have been registered  succesefully.Thank you";
		}
		String messagePage = "frontend/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);

	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
		dispatcher.forward(request, response);
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Customer customer = customerDAO.checkLogin(email, password);
		if (customer == null) {
			String message = "Login failed. Please check your email or password";
			request.setAttribute("message", message);
			showLogin();
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loggedCustomer", customer);
			Object objRedirectURL = session.getAttribute("redirectURL");
			if (objRedirectURL != null) {
				String redirectURL = (String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			} else {
				showCustomerProfile();
			}
		}

	}

	public void showCustomerProfile() throws ServletException, IOException {
		String profilePage = "frontend/customer_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
		dispatcher.forward(request, response);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		String editPage = "frontend/edit_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);

	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		updateCustomerFieldsFromForm(customer);
		customerDAO.update(customer);
		showCustomerProfile();

	}
}
