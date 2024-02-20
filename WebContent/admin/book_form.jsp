<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Book - Evergreen Bookstore Administration</title>
<link href="../css/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>

<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp" />


	<diV align="center">
		<h2>
			<c:if test="${book != null}">
			Edit Book
			</c:if>
			<c:if test="${book == null}">
			Create New Book
			</c:if>
		</h2>
	</diV>

	<diV align="center">
		<c:if test="${book != null}">
			<form id="bookForm" action="update_book" method="post"
				onsubmit="return validateFormInput() ">
				<input type="hidden" name="bookId" value=${book.bookId}>
		</c:if>
		<c:if test="${book == null}">
			<form id="bookForm" action="create_book" method="post"
				onsubmit="return validateFormInput() ">
		</c:if>
		<table class="form">
			<tbody>
				<tr>
					<td align="right">Category:</td>
					<td><select name="category">
							<c:forEach items="${listCategory}" var="category">
								<option value="${category.categoryId}">
									${category.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right">Title:</td>
					<td><input type="text" name="title" id="title" size="20"
						value=${book.title}></td>
				</tr>
				<tr>
					<td align="right">Author:</td>
					<td><input type="text" name="author" id="author" size="20"
						value=${book.author}></td>
				</tr>
				<tr>
					<td align="right">ISBN:</td>
					<td><input type="text" name="isbn" id="isbn" size="20"
						value=${book.isbn}></td>
				</tr>
				<tr>
					<td align="right">Publish Date:</td>
					<td><input type="text" name="publishDate" id="publishDate"
						size="20" value=${book.publishDate}></td>
				</tr>
				<tr>
					<td align="right">Book Image:</td>
					<td><input type="file" name="bookImage" id="bookImage"
						size="20"></td>
				</tr>
				<tr>
					<td align="right">Price:</td>
					<td><input type="text" name="price" id="price" size="20"
						value=${book.price}></td>
				</tr>
				<tr>
					<td align="right">Description:</td>
					<td align="left"><textarea id="description" name="description"
							rows="5" cols="50">
</textarea></td>
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
	$(document).ready(function() {
		$('#publishDate').datepicker();
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