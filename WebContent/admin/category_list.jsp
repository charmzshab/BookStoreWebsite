
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Category - EverGreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css" >
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Category Management</h2>
		<h3>
			 <a href="category_form.jsp" >Create New Category</a>
		</h3>

	</div>
	<c:if test="${someUserMessage != null}">
		<div align="center">
			<h4 class="message">
				${someUserMessage}
			</h4>
		</div>
	</c:if> 
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Category Name</th>
				<th>Actions</th>
			</tr>
			
			<c:forEach var="categoryIterator" items="${lisCategory}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${categoryIterator.categoryId}</td>
					<td>${categoryIterator.name}</td>
					
					<td>
					<a href="edit_category?id=${categoryIterator.categoryId}">Edit</a>  |
					 <a href="javascript:confirmDelete(${categoryIterator.categoryId})">Delete</a> 
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
	
	<!-- <script>
	$(document).ready(function() {
		$(".deleteLink").each(function() {
			$(this).on("click", function() {
				categoryId = $(this).attr("id");
				if (confirm('Are you sure you want to delete the category with ID ' +  categoryId + '?')) {
					window.location = 'delete_category?id=' + categoryId;
				}					
			});
		});
	});
	
	</script> -->
	
	<script>
	 function confirmDelete(categoryId) {
		 if(confirm('Are you sure you want tyo delete the category with  ' + categoryId + ' ?')){
			 window.location = 'delete_category?id='+ categoryId; 
		 }
		
	}
 	</script>
	  

</body>
</html>