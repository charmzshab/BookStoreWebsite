<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
			<form id="form-1" action="update_user" method="post"
			onsubmit="return validateFormInput() ">
			<input type="hidden" name="userId" value=${user.userId}>
			</c:if>
		<c:if test="${user == null}">
			<form id="form-1" action="create_user" method="post"
			onsubmit="return validateFormInput() ">
			</c:if>
		<form id="form-1" action="create_user" method="post"
			onsubmit="return validateFormInput() ">
			<table id="table-1">
				<tbody>
					<tr>
						<td align="right">Email:</td>
						<td><input type="email" name="mail" id="email" size="20"
							value=${user.email}></td>
					</tr>
					<tr>
						<td align="right">Full Name:</td>
						<td><input type="text" name="fullname" id="name" size="20"
							value=${fullName}></td>
					</tr>
					<tr>
						<td align="right">Password:</td>
						<td><input type="password" name="password" id="password"
							size="20" value=${user.password}></td>
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
	//const email2 = document.querySelector('input[type="email"]');

	//email2.addEventListener("keyup",()=>{

	//	console.log("hi");
	//});

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
</html>