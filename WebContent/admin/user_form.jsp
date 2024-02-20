<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<meta charset="ISO-8859-1">
<title>Create New User - Evergreen Bookstore Administration</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />


	<diV align="center">
		<h2>
			<c:if test="${user != null}">
			Edit User
			</c:if>
			<c:if test="${user == null}">
			Create New User
			</c:if>
		</h2>
	</diV>

	<diV align="center">
		<c:if test="${user != null}">
			<form id="userForm" action="update_user" method="post"">
				<input type="hidden" name="userId" value=${user.userId}>
		</c:if>
		<c:if test="${user == null}">
			<form id="userForm" action="create_user" method="post">
		</c:if>
		<table class="form">
			<tbody>
				<tr>
					<td align="right">Email:</td>
					<td align="left"><input type="text" name="mail" id="email"
						size="20" value=${user.email}></td>
				</tr>
				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" name="fullname" id="name"
						size="20" value=${fullName}></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" name="password"
						id="password" size="20" value=${user.password}></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save:"> <input type="button" value="Cancel"
						onClick="javascript:history.go(-1);"></td>
			</tbody>
		</table>
		</form>
	</diV>
	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
	function validateFormInput() {
		const emailField = document.getElementById("email");
		const fullNameField = document.getElementById("name");
		const passwordField = document.getElementById("password");

		if (emailField.value.length == 0) {
			alert("Email is required!!");
			emailField.focus();
			return false;
		}

		if (fullNameField.value.length == 0) {
			alert("Full Name is required!!");
			fullNameField.focus();
			return false;
		}

		if (passwordField.value.length == 0) {
			alert("Password is required!!");
			passwordField.focus();
			return false;
		}
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#userForm").validate({
			rules : {
				mail : {
					required : true,
					mail : true
				},
				fullname : "required",
				password : "required",
			},

			messages : {
				mail : {
					required : "Please enter email",
					mail : "Please enter a valid email address"
				},
				fullname : "Please enter full name",
				password : "Please enter password"
			}
		});

		$("#buttonCancel").click(function() {
			history.go(-1);
		});

	});
</script>
</html>