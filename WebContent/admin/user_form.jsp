<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New User</title>
<link rel="stylesheet" href="../css/style.css" >
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
		
     
	<div align="center">
	<h1 class="pageheading">
        <c:if test="${user != null}">
			Edit User
		</c:if>
		<c:if test="${user == null}">
			Create New User
		</c:if>
    </h1> 
     </div>
     
       <div align="center">
	    <c:if test="${user != null}">
	        <form action="update_user" method="post" onsubmit="validateFormInput()"/>
			<input type="hidden" name="userId" value="${user.userId }">
		</c:if>

        <c:if test="${user == null}">
			<form action="create_user" method="post" onsubmit="validateFormInput()"/>
		</c:if>
		
		<table class="form">
			<tr>
				<td align="right">Email:</td>
				<td align="left"><input type="text" id="email" name="email" value="${user.email}" size="20"></td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" id="fullName" name="fullName" value="${user.fullName}" size="20"></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" id="password"name="password" value="${user.password}" size="20"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
			<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
		     <button onclick="javascript:history.go(-1);"> Cancel  </button>
					    </td>
			</tr>
		</table>
		</form>
	</div>


	<jsp:directive.include file="footer.jsp" />

</body>
<script type="text/javascript">
	function validateFormInput() {
		var fieldEmail = document.getElementById("email");
		var fieldFullName = document.getElementById("fullName");
		var fieldPassword = document.getElementById("password");
		if (fieldEmail.value.length == 0) {
			alert("Email is required");
			fieldEmail.focus();
			return false;
		}
		if (fieldFullName.value.length == 0) {
			alert("Full Name  is required");
			fieldFullName.focus();
			return false;
		}
		if (fieldPassword.value.length == 0) {
			alert("Password  is required");
			fieldPassword.focus();
			return false;
		}
		return true;

	}
</script>
</html>