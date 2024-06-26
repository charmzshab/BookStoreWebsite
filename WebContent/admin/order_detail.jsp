
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Details - EverGreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Details of Orders Id : ${order.orderId}</h2>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>

	<div align="center">
		<h2>Order Overview</h2>
		<table>
			<tr>
				<td><b>Ordered by :</b></td>
				<td>${order.customer.fullname}</td>
			</tr>

			<tr>
				<td><b>Book Copies :</b></td>
				<td>${order.bookCopies}</td>
			</tr>

			<tr>
				<td><b>Toatal Amount :</b></td>
				<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
			</tr>

			<tr>
				<td><b>Recipient name :</b></td>
				<td>${order.recipientName}</td>
			</tr>


			<tr>
				<td><b>Recipient phone:</b></td>
				<td>${order.recipientPhone}</td>
			</tr>

			<tr>
				<td><b>Payment:</b></td>
				<td>${order.paymentMethod}</td>
			</tr>

			<tr>
				<td><b>Shipping adderess:</b></td>
				<td>${order.shippingAddress}</td>
			</tr>
			<tr>
				<td><b>Order Status:</b></td>
				<td>${order.status}</td>
			</tr>

			<tr>
				<td><b>Order date:</b></td>
				<td>${order.orderDate}</td>
			</tr>

		</table>
	</div>


	<div align="center">
		<h2>Ordered Books:</h2>
		<table border="1">
			<th>Index</th>
			<th>Book Title</th>
			<th>Author</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>SubTotal</th>

			<c:forEach items="${order.orderDetails}" var="orderDetail"
				varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${orderDetail.book.title}</td>
					<td>${orderDetail.book.author}</td>
					<td><fmt:formatNumber value="${orderDetail.book.price}" type="currency" /></td>
					<td>${oderDetail.quantity}</td>
					<td><fmt:formatNumber value="${orderDetail.subtotal}" type="currency" /></td>
					<td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right">
					<b><i>TOTAL:</i></b>
				</td>
				<td>
					<b>${order.bookCopies}</b>
				</td>
				<td>
					<b><fmt:formatNumber value="${order.total}" type="currency" /></b>
				</td>
			</tr>
		</table>
	</div>

	<div align="center">
	<br/>
		<a href="edit_order?id=${order.orderId}">Edit this order</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<a href="">Delete this order</a>
	</div>



	<jsp:directive.include file="footer.jsp" />




</body>
</html>