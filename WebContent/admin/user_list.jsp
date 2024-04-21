
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Users - EverGreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css" >
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">User Management</h2>
		<h3>
			<a href="user_form.jsp" >Create New User</a>
		</h3>

	</div>
	<c:if test="${someUserMessage != null}">
		<div  align="center">
			<h4  class="message">
				${someUserMessage}
			</h4>
		</div>
	</c:if> 
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Email</th>
				<th>Fullname</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="userIterator" items="${listUsers}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${userIterator.userId}</td>
					<td>${userIterator.email}</td>
					<td>${userIterator.fullName}</td>
					<td>
					<a href="edit_user?id=${userIterator.userId}">Edit</a> 
					<a href="javascript:confirmDelete(${userIterator.userId})">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
	<script>
	
	
	 function confirmDelete(userId) {
		 if(confirm('Are you sure you want tyo delete the user ' + userId + ' ?')){
			 window.location = 'delete_user?id='+ userId; 
		 }
		
	} 
	
	</script>
	

</body>
</html>