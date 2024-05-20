<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ever Green - Online Book Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h3>Welcome, ${loggedCustomer.fullname}</h3>
		<br />

		<table class="normal">
			<tr>
				<td><b>E-mail address : </b></td>
				<td>${loggedCustomer.email}</td>
			</tr>

			<tr>
				<td><b>Full Name: </b></td>
				<td>${loggedCustomer.fullname}</td>
			</tr>

			<tr>
				<td><b>Phone Number : </b></td>
				<td>${loggedCustomer.phone}</td>
			</tr>

			<tr>
				<td><b>Address: </b></td>
				<td>${loggedCustomer.address}</td>
			</tr>

			<tr>
				<td><b>City: </b></td>
				<td>${loggedCustomer.city}</td>
			</tr>

			<tr>
				<td><b>Zip Code: </b></td>
				<td>${loggedCustomer.zipcode}</td>
			</tr>

			<tr>
				<td><b>Country: </b></td>
				<td>${loggedCustomer.country}</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
			<td  colspan="2" align="center"><b><a href = "edit_profile">Edit my profile</a></b> </td>
			</tr>
		</table>

	</div>



	<jsp:directive.include file="footer.jsp" />

</body>
</html>